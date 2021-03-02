package com.mac.bry.crud.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mac.bry.crud.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    List<User> findByName(String name);
    
}
