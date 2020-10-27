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
import com.javasampleapproach.springrest.cassandra.model.Author;
import com.javasampleapproach.springrest.cassandra.repo.AuthorRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class AuthorController {

	@Autowired
	AuthorRepository repository;

	@GetMapping("/authors")
	public List<Author> getAllAuthors() {
		System.out.println("Get all authors...");

		return repository.findAll();
	}

	@PostMapping("/authors/create")
	public ResponseEntity<Author> postProvider(@RequestBody Author author) {
		try {
			Author _author = repository.save(new Author(UUIDs.timeBased(), author.getName()));
		    return new ResponseEntity<>(_author, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/authors/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") UUID id) {
		System.out.println("Delete author with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Author has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/authors/delete")
	public ResponseEntity<String> deleteAllAuthors() {
		System.out.println("Delete All authors...");

		repository.deleteAll();

		return new ResponseEntity<>("All authors have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/authors/id/{id}")
	public Optional<Author> findById(@PathVariable UUID id) {

		Optional<Author> authors = repository.findById(id);
		return authors;
	}

	@PutMapping("/authors/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") UUID id, @RequestBody Author author) {
		System.out.println("Update author with ID = " + id + "...");

		Optional<Author> authorData = repository.findById(id);

		if (authorData.isPresent()) {
			Author _author = authorData.get();
			_author.setName(author.getName());
			return new ResponseEntity<>(repository.save(_author), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

