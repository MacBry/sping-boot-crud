package com.mac.bry.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mac.bry.crud.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
	
	UserRole findByRole(String role);
	
}
