package com.company.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.ums.model.User;
import com.company.ums.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(path = "users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping(path = "users/{user-id}")
	public ResponseEntity<User> getUserById(@PathVariable("user-id") Long id) {
		User article = userService.findById(id);
		if (article != null) {
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(path = "users/{user-id}")
	public ResponseEntity<User> updateUser(@PathVariable("user-id") Long id, @RequestBody User user) {
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}

	@DeleteMapping(path = "users/{user-id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("user-id") Long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
