package com.mac.bry.crud.MVCcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mac.bry.crud.entities.UserDescription;
import com.mac.bry.crud.services.UserService;

@Controller
public class UserDetailsController {

	private UserService userService;
	private long id;

	@Autowired
	public UserDetailsController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/userDescription/{id}")
	public String showDescription(@PathVariable("id") long id, Model model) {
		model.addAttribute("userDescription", userService.showAllDescription(id));
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
		userService.addDescriptionToUser(id, userDescriptions);
		return "redirect:/index";
	}

	@GetMapping("/editDescription/{id}")
	public String showUpdateDetailForm(@PathVariable("id") long id, Model model) {
		UserDescription description = userService.findUserDescriptionById(id);
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
		userService.updateDescription(id, description);

		return "redirect:/index";
	}

	@GetMapping("/deleteDescription/{id}")
	public String deleteUserDescription(@PathVariable("id") long id, Model model) {
		userService.deleteUserDescription(id);
		return "redirect:/index";
	}
}
