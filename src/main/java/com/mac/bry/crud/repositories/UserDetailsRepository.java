package com.mac.bry.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mac.bry.crud.entities.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

}
