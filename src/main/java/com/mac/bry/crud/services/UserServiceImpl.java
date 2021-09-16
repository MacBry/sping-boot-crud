package com.mac.bry.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.entities.UserDescription;
import com.mac.bry.crud.repositories.UserDescriptionRepository;
import com.mac.bry.crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepository;
	private UserDescriptionRepository userDescriptionRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserDescriptionRepository userDescriptionRepository) {
		super();
		this.userRepository = userRepository;
		this.userDescriptionRepository = userDescriptionRepository;
	}
	
	@Override
	public User findUserById(long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}
	
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	@Override
	public void deleteUser(long id) {
		User user = findUserById(id);
		userRepository.delete(user);
	}
	
	@Override
	public void addDescriptionToUser (long id, UserDescription userDescription) {
		User user = findUserById(id);
		userDescription.setUser(user);
		user.addDescription(userDescription);
		userDescriptionRepository.save(userDescription);
		userRepository.save(user);
	}
	
	@Override
	public UserDescription findUserDescriptionById(long id) {
		return userDescriptionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid description Id:" + id));
	}
	
	@Override
	public void updateDescription(long id, UserDescription userDescription) {
		User user = findUserById(id);
		userDescription.setUser(user);
		userDescriptionRepository.save(userDescription);
		userRepository.save(user);
	}
	
	@Override
	public void deleteUserDescription (long id) {
		UserDescription userDescription = findUserDescriptionById(id);
		userDescription.setUser(null);
		userDescriptionRepository.delete(userDescription);
		
	}
}
