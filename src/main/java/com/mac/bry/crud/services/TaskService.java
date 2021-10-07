package com.mac.bry.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.bry.crud.entities.Task;
import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.repositories.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	private UserService userService;
	
	public TaskService(TaskRepository taskRepository, UserService userService) {
		super();
		this.taskRepository = taskRepository;
		this.userService = userService;
	}
	
	public TaskService() {
		super();
	}

	@Autowired
	public void setTaskRepository(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Iterable<Task> findAllTasks (){
		return taskRepository.findAll();
	}
	
	public Iterable<Task> findAllUserTasks(long userId){
		User user = userService.findUserById(userId);
		return user.getUserTasks();
	}
	
}
