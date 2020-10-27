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
import com.javasampleapproach.springrest.cassandra.model.Shipper;
import com.javasampleapproach.springrest.cassandra.repo.ShipperRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class ShipperController {

	@Autowired
	ShipperRepository repository;

	@GetMapping("/shippers")
	public List<Shipper> getAll() {
		System.out.println("Get all shippers...");

		return repository.findAll();
	}

	@PostMapping("/shippers/create")
	public ResponseEntity<Shipper> postData(@RequestBody Shipper data) {
		try {
			Shipper _data = repository.save(new Shipper(UUIDs.timeBased(), data.getName(), data.getPrice()));
		    return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/shippers/{id}")
	public ResponseEntity<String> deleteData(@PathVariable("id") UUID id) {
		System.out.println("Delete shipper with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("shipper has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/shippers/delete")
	public ResponseEntity<String> deleteAllDatas() {
		System.out.println("Delete All shippers...");

		repository.deleteAll();

		return new ResponseEntity<>("All shippers have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/shippers/id/{id}")
	public Optional<Shipper> findById(@PathVariable UUID id) {

		Optional<Shipper> datas = repository.findById(id);
		return datas;
	}

	@PutMapping("/shippers/{id}")
	public ResponseEntity<Shipper> updateData(@PathVariable("id") UUID id, @RequestBody Shipper data) {
		System.out.println("Update Publisher with ID = " + id + "...");

		Optional<Shipper> pData = repository.findById(id);

		if (pData.isPresent()) {
			Shipper _data = pData.get();
			_data.setName(data.getName());
			_data.setPrice(data.getPrice());
			return new ResponseEntity<>(repository.save(_data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

