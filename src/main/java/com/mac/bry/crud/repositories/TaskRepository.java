package com.mac.bry.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mac.bry.crud.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
 
}
