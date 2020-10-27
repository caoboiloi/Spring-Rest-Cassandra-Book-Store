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
import com.javasampleapproach.springrest.cassandra.model.Provider;
import com.javasampleapproach.springrest.cassandra.repo.ProviderRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class ProviderController {

	@Autowired
	ProviderRepository repository;

	@GetMapping("/providers")
	public List<Provider> getAllProviders() {
		System.out.println("Get all Providers...");

		return repository.findAll();
	}

	@PostMapping("/providers/create")
	public ResponseEntity<Provider> postProvider(@RequestBody Provider provider) {
		try {
			Provider _provider = repository.save(new Provider(UUIDs.timeBased(), provider.getName()));
		    return new ResponseEntity<>(_provider, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/providers/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable("id") UUID id) {
		System.out.println("Delete Provider with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Provider has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/providers/delete")
	public ResponseEntity<String> deleteAllProviders() {
		System.out.println("Delete All providers...");

		repository.deleteAll();

		return new ResponseEntity<>("All providers have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/providers/id/{id}")
	public Optional<Provider> findById(@PathVariable UUID id) {

		Optional<Provider> providers = repository.findById(id);
		return providers;
	}

	@PutMapping("/providers/{id}")
	public ResponseEntity<Provider> updateProvider(@PathVariable("id") UUID id, @RequestBody Provider provider) {
		System.out.println("Update provider with ID = " + id + "...");

		Optional<Provider> providerData = repository.findById(id);

		if (providerData.isPresent()) {
			Provider _provider = providerData.get();
			_provider.setName(provider.getName());
			return new ResponseEntity<>(repository.save(_provider), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

