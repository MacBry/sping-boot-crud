package com.mac.bry.crud.MVCcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mac.bry.crud.entities.Task;
import com.mac.bry.crud.services.TaskService;

@Controller
public class TaskController {
	
	private TaskService taskService;
	private long id;
	
	@Autowired
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	@GetMapping("/userTask/{id}")
	public String showTasks(@PathVariable("id") long id, Model model) {
		model.addAttribute("task", taskService.findAllUserTasks(id));
		this.id = id;
		return "show-tasks";
	}
	
	@GetMapping("/showAddTaskForm")
	public String showUserTaskForm(Task task) {
		return "add-task";
	}
	
	@PostMapping("/addUserTask")
	public String addUserTask(@Valid Task task, BindingResult result, Model model) {
		model.addAttribute(task);
		
		if(result.hasFieldErrors()) {
			return "add-task";
		}
		System.out.println(task.getTaskStatus().toString());
		taskService.addTaskToUser(id, task);
		return "show-tasks";
	}

}
