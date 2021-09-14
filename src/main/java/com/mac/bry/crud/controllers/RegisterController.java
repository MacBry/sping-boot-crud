package com.mac.bry.crud.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.services.UserService;

@Controller
public class RegisterController {
	
	private UserService userService;
    
	@Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }
	

	@GetMapping("/register")
    public String register(User user) {
    	return "register-form";
    }
    
    @PostMapping("/registerform")
    public String addRegisterUser(@Valid User user, BindingResult result, Model model) {
    	if(result.hasErrors())
		return "register-form";
    	else {
		userService.addWithDefaultRole(user);
		return "redirect:/index";
    	}
	}
}
