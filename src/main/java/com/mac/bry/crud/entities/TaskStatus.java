package com.mac.bry.crud.entities;

public enum TaskStatus {
	TASK_COMPLETED("TASK COMPLETED"),
	TASK_IN_PROGRESS("TASK IN PROGRESS"),
	TASK_CANCELED("TASK CANCELED"),
	TASK_POSTPONED("Task POSTPONED");
	
	private final String decription;
	
	private TaskStatus(String description) {
		this.decription = description;
	}
	public String getDescription() {
		return decription;
	}
}
