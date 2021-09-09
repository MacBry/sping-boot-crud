package com.mac.bry.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.entities.UserRole;
import com.mac.bry.crud.repositories.UserRepository;
import com.mac.bry.crud.repositories.UserRoleRepository;

public class UserService {
	
	private static final String DEFAULT_ROLE ="ROLE_USER";
	
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}
	
	public void addWithDefaultRole(User user) {
		UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
		user.getRoles().add(defaultRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		userRepository.save(user);
	}
	

}
