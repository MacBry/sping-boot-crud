package com.mac.bry.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac.bry.crud.entities.Task;
import com.mac.bry.crud.entities.User;
import com.mac.bry.crud.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;
	private UserService userService;
	
	public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
		super();
		this.taskRepository = taskRepository;
		this.userService = userService;
	}
	
	public TaskServiceImpl() {
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
	
	@Override
	public Iterable<Task> findAllTasks (){
		return taskRepository.findAll();
	}
	
	@Override
	public Iterable<Task> findAllUserTasks(long userId){
		User user = userService.findUserById(userId);
		return user.getUserTasks();
	}
	
	@Override
	public Task findUserTaskByID(long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid description id:"+ id));
	}
	
	@Override
	public void addTaskToUser(long userId, Task task) {
		User user = userService.findUserById(userId);
		task.setUser(user);
		user.addTaks(task);
		taskRepository.save(task);
	}
	
	@Override
	public void upadateTask (long userId, Task task) {
		User user = userService.findUserById(userId);
		task.setUser(user);
		taskRepository.save(task);
		userService.updateUser(user);
	}
	
	@Override
	public void deleteTask (long id) {
		Task task = findUserTaskByID(id);
		task.setUser(null);
		taskRepository.delete(task);
	}
}
