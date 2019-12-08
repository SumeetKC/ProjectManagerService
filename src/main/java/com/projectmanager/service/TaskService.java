/**
 * 
 */
package com.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.dao.TaskDaoImpl;
import com.projectmanager.model.Task;

/**
 * @author SumeetK
 *
 */

@CrossOrigin(
	    allowCredentials = "true",
	    origins = "*", 
	    allowedHeaders = "*", 
	    methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
	)
@RestController
@RequestMapping("/v1")
public class TaskService {

	/** Initialize the LOGGER object */
	// private static final Log logger = LogFactory.getLog(TaskService.class);

	@Autowired
	private TaskDaoImpl taskDaoImpl;

	@PostMapping(value = "/addtask", produces = MediaType.APPLICATION_JSON_VALUE)
	public Task addTask(@Valid @RequestBody Task task) {
		return taskDaoImpl.addTask(task);
	}

	@PutMapping(value = "/updatetask", produces = MediaType.APPLICATION_JSON_VALUE)
	public Task updateTask(@Valid @RequestBody Task task) {
		
		return taskDaoImpl.updateTask(task);
	}

	@GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getTasks() {
		
		List<Task> taskList = new ArrayList<Task>();
		taskList = taskDaoImpl.getTasks();
		return taskList;
	}

	@PutMapping(value = "/updateendstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public Task updateEndStatus(@Valid @RequestBody Task task) {
	
		return taskDaoImpl.updateEndTask(task);
	}
	
	@GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Task getTask(@PathVariable int id) {
		
		Task task = new Task();
		task = taskDaoImpl.getTask(id);
		return task;
	}

}
