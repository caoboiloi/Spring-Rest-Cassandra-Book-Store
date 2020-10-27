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
import com.javasampleapproach.springrest.cassandra.model.Admin;
import com.javasampleapproach.springrest.cassandra.model.User;
import com.javasampleapproach.springrest.cassandra.repo.AdminRepository;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	AdminRepository repository;

	@GetMapping("/admins")
	public List<Admin> getAll() {
		System.out.println("Get all admins...");

		return repository.findAll();
	}

	@PostMapping("/admins/create")
	public ResponseEntity<Admin> postData(@RequestBody Admin data) {
		try {
			Admin _data = repository.save(
					new Admin(
					UUIDs.timeBased(), 
					data.getName(), 
					data.getUsername(),
					data.getPass(),
					data.getRole(),
					data.getAccess()
					));
		    return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/admins/{id}")
	public ResponseEntity<String> deleteData(@PathVariable("id") UUID id) {
		System.out.println("Delete admin with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("shipper has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/admins/delete")
	public ResponseEntity<String> deleteAllDatas() {
		System.out.println("Delete All admins...");

		repository.deleteAll();

		return new ResponseEntity<>("All admins have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/admins/id/{id}")
	public Optional<Admin> findById(@PathVariable UUID id) {

		Optional<Admin> datas = repository.findById(id);
		return datas;
	}
	
	@GetMapping("/admins/username/{username}")
	public List<Admin> findByUsername(@PathVariable String username) {
		List<Admin> admins = repository.findByUsername(username);
		return admins;
	}

	@PutMapping("/admins/{id}")
	public ResponseEntity<Admin> updateData(@PathVariable("id") UUID id, @RequestBody Admin data) {
		System.out.println("Update Publisher with ID = " + id + "...");

		Optional<Admin> pData = repository.findById(id);

		if (pData.isPresent()) {
			Admin _data = pData.get();
			_data.setName(data.getName());
			_data.setUsername(data.getUsername());
			_data.setRole(data.getRole());
			_data.setPass(data.getPass());
			_data.setAccess(data.getAccess());
			return new ResponseEntity<>(repository.save(_data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

