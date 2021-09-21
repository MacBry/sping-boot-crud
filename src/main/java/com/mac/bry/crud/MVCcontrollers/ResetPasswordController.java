package com.mac.bry.crud.MVCcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mac.bry.crud.services.UserService;


@Controller
public class ResetPasswordController {
	
	private UserService userService;

	@Autowired
	public ResetPasswordController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/resetPasswordForm")
	public String getPasswordResetForm () {
		return "reset-password-form";
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(String mail) {
		if( userService.findUserByMail(mail)== null) {
			return "reset-password-form";
		}
		userService.resetPassword(mail);
		return "redirect:/index";
		
	}
}
