package com.javasampleapproach.springrest.cassandra.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;
import com.javasampleapproach.springrest.cassandra.model.User;
import com.javasampleapproach.springrest.cassandra.repo.UserRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository repository;

	@GetMapping("/users")
	public List<User> getAllUser() {
		System.out.println("Get all Users...");

		return repository.findAll();
	}

	@PostMapping("/users/create")
	public ResponseEntity<User> postUser(@RequestBody User user) {
		try {
			User _user = repository.save(
					new User(
							UUIDs.timeBased(), 
							user.getUsername(), 
							user.getPass(), 
							user.getRole(),
							user.getName(),
							user.getAddress(),
							user.getSex(),
							user.getAge()));
		    return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
		System.out.println("Delete User with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/users/delete")
	public ResponseEntity<String> deleteAllUsers() {
		System.out.println("Delete All Users...");

		repository.deleteAll();

		return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
	}
	
	@GetMapping("/users/username/{username}")
	public List<User> findByUsername(@PathVariable String username) {
		List<User> users = repository.findByUsername(username);
		return users;
	}

	@GetMapping("/users/id/{id}")
	public Optional<User> findById(@PathVariable UUID id) {

		Optional<User> users = repository.findById(id);
		return users;
	}
	
	@GetMapping("/users/sex/{sex}")
	public List<User> findBySex(@PathVariable String sex) {
		List<User> users = repository.findBySex(sex);
		return users;
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
		System.out.println("Update User with ID = " + id + "...");

		Optional<User> userData = repository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setUsername(user.getUsername());
			_user.setPass(user.getPass());
			_user.setRole(user.getRole());
			_user.setName(user.getName());
			_user.setAddress(user.getAddress());
			_user.setSex(user.getSex());
			_user.setAge(user.getAge());
			return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

