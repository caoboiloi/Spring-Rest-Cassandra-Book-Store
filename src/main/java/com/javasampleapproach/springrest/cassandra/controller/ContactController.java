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
import com.javasampleapproach.springrest.cassandra.model.Contact;
import com.javasampleapproach.springrest.cassandra.repo.ContactRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	ContactRepository repository;

	@GetMapping("/contacts")
	public List<Contact> getAll() {
		System.out.println("Get all Contacts...");

		return repository.findAll();
	}

	@PostMapping("/contacts/create")
	public ResponseEntity<Contact> postData(@RequestBody Contact data) {
		try {
			Contact _data = repository.save(
					new Contact(
							UUIDs.timeBased(), 
							data.getName(), 
							data.getMail(),
							data.getMessage()
							)
					);
		    return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<String> deleteData(@PathVariable("id") UUID id) {
		System.out.println("Delete contact with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("contact has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/contacts/delete")
	public ResponseEntity<String> deleteAllDatas() {
		System.out.println("Delete All contacts...");

		repository.deleteAll();

		return new ResponseEntity<>("All contacts have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/contacts/id/{id}")
	public Optional<Contact> findById(@PathVariable UUID id) {

		Optional<Contact> datas = repository.findById(id);
		return datas;
	}

	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateData(@PathVariable("id") UUID id, @RequestBody Contact data) {
		System.out.println("Update Contact with ID = " + id + "...");

		Optional<Contact> pData = repository.findById(id);

		if (pData.isPresent()) {
			Contact _data = pData.get();
			_data.setName(data.getName());
			_data.setMail(data.getMail());
			_data.setMessage(data.getMessage());
			return new ResponseEntity<>(repository.save(_data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

