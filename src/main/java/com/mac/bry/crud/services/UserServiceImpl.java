package com.mac.bry.crud.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.entities.UserDescription;
import com.mac.bry.crud.repositories.UserDescriptionRepository;
import com.mac.bry.crud.repositories.UserRepository;
import com.mac.bry.crud.utilitis.EmailSender;

@Service
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepository;
	private UserDescriptionRepository userDescriptionRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserDescriptionRepository userDescriptionRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.userDescriptionRepository = userDescriptionRepository;
		this.passwordEncoder =passwordEncoder;
	}
	
	@Override
	public User findUserByMail(String mail) {
		return userRepository.findByEmail(mail);
	}
	
	@Override
	public Iterable<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public User findUserById(long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}
	
	@Override
	public void addUser(User user) {
		hashPassword(user);
		userRepository.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		hashPassword(user);
		userRepository.save(user);
	}
	
	@Override
	public void deleteUser(long id) {
		User user = findUserById(id);
		userRepository.delete(user);
	}
	
	@Override	
	public void resetUserPassword(String mail) {
		
	}
	
	@Override
	public Iterable<UserDescription> showAllDescription(long id) {
		User user = findUserById(id);
		return  user.getUserDescription();
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
	
	private void hashPassword(User user) {
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
	}
	
	private String generateRandomPassword() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return  buffer.toString();

	}
	
	@Override
	public void resetPassword(String mail) {
		String newPassword = generateRandomPassword();
		User user = userRepository.findByEmail(mail);
		user.setPassword(newPassword);
		hashPassword(user);
		userRepository.save(user);
		EmailSender.configureMail(mail, newPassword);
	}
}
