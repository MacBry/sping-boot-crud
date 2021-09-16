package com.mac.bry.crud.services;

import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.entities.UserDescription;

public interface UserService {

	User findUserById(long id);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(long id);

	void addDescriptionToUser(long id, UserDescription userDescription);

	UserDescription findUserDescriptionById(long id);

	void updateDescription(long id, UserDescription userDescription);

	void deleteUserDescription(long id);

}