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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.dao.UserDaoImpl;
import com.projectmanager.model.User;

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
public class UserService {

	/** Initialize the LOGGER object */
	// private static final Log logger = LogFactory.getLog(UserService.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	@PostMapping(value = "/adduser", produces = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@Valid @RequestBody User user) {
		return userDaoImpl.addUser(user);
	}

	@PostMapping(value = "/updateuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@Valid @RequestBody User user) {
		
		return userDaoImpl.updateUser(user);
	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers() {
		
		List<User> userList = new ArrayList<User>();
		userList = userDaoImpl.getUsers();
		return userList;
	}
	
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable int id) {
		
		User user = new User();
		user = userDaoImpl.getUser(id);
		return user;
	}

}
