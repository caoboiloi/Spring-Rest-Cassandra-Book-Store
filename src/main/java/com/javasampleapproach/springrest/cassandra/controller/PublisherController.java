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
import com.javasampleapproach.springrest.cassandra.model.Publisher;
import com.javasampleapproach.springrest.cassandra.repo.PublisherRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class PublisherController {

	@Autowired
	PublisherRepository repository;

	@GetMapping("/publishers")
	public List<Publisher> getAllPublishers() {
		System.out.println("Get all Publishers...");

		return repository.findAll();
	}

	@PostMapping("/publishers/create")
	public ResponseEntity<Publisher> postPublisher(@RequestBody Publisher publisher) {
		try {
			Publisher _publisher = repository.save(new Publisher(UUIDs.timeBased(), publisher.getName()));
		    return new ResponseEntity<>(_publisher, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/publishers/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable("id") UUID id) {
		System.out.println("Delete Publisher with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Publisher has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/publishers/delete")
	public ResponseEntity<String> deleteAllPublishers() {
		System.out.println("Delete All Publishers...");

		repository.deleteAll();

		return new ResponseEntity<>("All publishers have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/publishers/id/{id}")
	public Optional<Publisher> findById(@PathVariable UUID id) {

		Optional<Publisher> publishers = repository.findById(id);
		return publishers;
	}

	@PutMapping("/publishers/{id}")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable("id") UUID id, @RequestBody Publisher publisher) {
		System.out.println("Update Publisher with ID = " + id + "...");

		Optional<Publisher> publisherData = repository.findById(id);

		if (publisherData.isPresent()) {
			Publisher _publisher = publisherData.get();
			_publisher.setName(publisher.getName());
			return new ResponseEntity<>(repository.save(_publisher), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

