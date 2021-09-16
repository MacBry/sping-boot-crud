package com.mac.bry.crud.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.entities.UserDescription;
import com.mac.bry.crud.repositories.UserDescriptionRepository;
import com.mac.bry.crud.repositories.UserRepository;

@Controller
public class UserDetailsController {

	private final UserRepository userRepository;
	private final UserDescriptionRepository userDescriptionRepository;
	private long id;

	@Autowired
	public UserDetailsController(UserRepository userRepository, UserDescriptionRepository detailRepository) {
		super();
		this.userRepository = userRepository;
		this.userDescriptionRepository = detailRepository;
	}

	@GetMapping("/userDescription/{id}")
	public String showDetail(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		List<UserDescription> userDescription = user.getUserDescription();
		model.addAttribute("userDescription", userDescription);
		this.id = id;
		return "show-description";
	}

	@GetMapping("/showAddDescriptionForm")
	public String showUserDetailForm(UserDescription userDescription) {
		return "add-description";
	}

	@PostMapping("/addUserDescription")
	public String addUserDescription(@Valid UserDescription userDescriptions, BindingResult result, Model model) {
		model.addAttribute(userDescriptions);
		if (result.hasErrors()) {
			return "add-user";
		}
		
		User user = userRepository.findById(this.id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + this.id));
		userDescriptions.setUser(user);
		user.addDescription(userDescriptions);
		userDescriptionRepository.save(userDescriptions);
		userRepository.save(user);
		return "redirect:/index";
	}

	@GetMapping("/editDescription/{id}")
	public String showUpdateDetailForm(@PathVariable("id") long id, Model model) {
		UserDescription description = userDescriptionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid detail Id:" + id));
		model.addAttribute("description", description);

		return "update-description";
	}

	@PostMapping("/updateDescription/{id}")
	public String updateUserDescription(@PathVariable("id") long id, @Valid UserDescription description, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			description.setId(id);
			return "update-description";
		}
		User user = userRepository.findById(this.id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + this.id));

		description.setUser(user);
		userDescriptionRepository.save(description);
		userRepository.save(user);

		return "redirect:/index";
	}

	@GetMapping("/deleteDescription/{id}")
	public String deleteUserDescription(@PathVariable("id") long id, Model model) {
		UserDescription description = userDescriptionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid detail Id:" + id));
		description.setUser(null);
		userDescriptionRepository.delete(description);
		return "redirect:/index";
	}
}
