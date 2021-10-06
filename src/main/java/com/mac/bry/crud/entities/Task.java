package com.mac.bry.crud.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Task_ID")
	private long id;
	
	private TaskStatus taskStatus;
	
	private LocalDate taskStartingDate;
	
	private LocalDate taskEndDate;
	
	private String taskDescription;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Task(TaskStatus taskStatus, LocalDate taskStartingDate, LocalDate taskEndDate, String taskDescription,
			User user) {
		super();
		this.taskStatus = taskStatus;
		this.taskStartingDate = taskStartingDate;
		this.taskEndDate = taskEndDate;
		this.taskDescription = taskDescription;
		this.user = user;
	}

	public Task() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public LocalDate getTaskStartingDate() {
		return taskStartingDate;
	}

	public void setTaskStartingDate(LocalDate taskStartingDate) {
		this.taskStartingDate = taskStartingDate;
	}

	public LocalDate getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(LocalDate taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskStatus=" + taskStatus + ", taskStartingDate=" + taskStartingDate
				+ ", taskEndDate=" + taskEndDate + ", taskDescription=" + taskDescription + ", user=" + user + "]";
	}
	
	
	
}
