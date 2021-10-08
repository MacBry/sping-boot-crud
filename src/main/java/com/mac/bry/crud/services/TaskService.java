package com.mac.bry.crud.services;

import com.mac.bry.crud.entities.Task;

public interface TaskService {

	Iterable<Task> findAllTasks();

	Iterable<Task> findAllUserTasks(long userId);

	Task findUserTaskByID(long id);

	void addTaskToUser(long userId, Task task);

	void upadateTask(long userId, Task task);

	void deleteTask(long id);

}