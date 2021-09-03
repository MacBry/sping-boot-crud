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
import com.mac.bry.crud.entities.UserDetails;
import com.mac.bry.crud.repositories.UserDetailsRepository;
import com.mac.bry.crud.repositories.UserRepository;

@Controller
public class UserDetailsController {

	private final UserRepository userRepository;
	private final UserDetailsRepository detailRepository;
	private long id;

	@Autowired
	public UserDetailsController(UserRepository userRepository, UserDetailsRepository detailRepository) {
		super();
		this.userRepository = userRepository;
		this.detailRepository = detailRepository;
	}

	@GetMapping("/userDetail/{id}")
	public String showDetail(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		List<UserDetails> userDetails = user.getUserDetails();
		model.addAttribute("userDetails", userDetails);
		this.id = id;
		return "show-detail";
	}

	@GetMapping("/showAddDetailForm")
	public String showUserDetailForm(UserDetails userDetails) {
		return "add-detail";
	}

	@PostMapping("/addUserDetail")
	public String addUser(@Valid UserDetails userDetails, BindingResult result, Model model) {
		model.addAttribute(userDetails);
		if (result.hasErrors()) {
			return "add-user";
		}

		User user = userRepository.findById(this.id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + this.id));
		userDetails.setUser(user);
		user.addDetail(userDetails);
		detailRepository.save(userDetails);
		userRepository.save(user);
		return "redirect:/index";
	}

	@GetMapping("/editDetail/{id}")
	public String showUpdateDetailForm(@PathVariable("id") long id, Model model) {
		UserDetails details = detailRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid detail Id:" + id));
		model.addAttribute("detail", details);

		return "update-detail";
	}

	@PostMapping("/updateDetail/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid UserDetails detail, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			detail.setId(id);
			return "update-detail";
		}
		User user = userRepository.findById(this.id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + this.id));

		detail.setUser(user);
		detailRepository.save(detail);
		userRepository.save(user);

		return "redirect:/index";
	}

	@GetMapping("/deleteDetail/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		UserDetails detail = detailRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid detail Id:" + id));
		detail.setUser(null);
		detailRepository.delete(detail);
		return "redirect:/index";
	}
}
