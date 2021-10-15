package com.mac.bry.crud.MVCcontrollers;

import javax.validation.Valid;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.services.UserRegistrationService;

@Controller
public class RegisterController {
	
	private UserRegistrationService userRegistrationService;
    
	@Autowired
    public RegisterController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
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
		userRegistrationService.addWithDefaultRole(user);
		return "redirect:/index";
    	}
	}
}
