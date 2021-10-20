package com.mac.bry.crud.services;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.entities.UserRole;
import com.mac.bry.crud.entities.UserRoles;
import com.mac.bry.crud.repositories.UserRepository;
import com.mac.bry.crud.repositories.UserRoleRepository;

@Service
public class UserRegistrationService {
	
	
	private UserRepository userRepository;
	private UserRoleRepository userRoleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserRegistrationService(PasswordEncoder passwordEncoder) {
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
	
	@Audit(action = "UserRegistrationService.addWithDefaultRole")
	public void addWithDefaultRole(User user) {
		UserRole defaultRole = userRoleRepository.findByRole(UserRoles.DEFAULT_ROLE.getDescription());
		user.getRoles().add(defaultRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		userRepository.save(user);
	}
	

}
