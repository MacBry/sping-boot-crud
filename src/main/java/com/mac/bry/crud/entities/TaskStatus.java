package com.mac.bry.crud.entities;

public enum TaskStatus {
	TASK_COMPLETED("Task completed"),
	TASK_IN_PROGRESS("Task in progress"),
	TASK_CANCELED("Task canceled"),
	TASK_POSTPONED("Task postponed");
	
	private final String decription;
	
	private TaskStatus(String description) {
		this.decription = description;
	}
	public String getDescription() {
		return decription;
	}
}
