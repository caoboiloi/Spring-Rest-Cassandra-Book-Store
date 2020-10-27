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
import com.javasampleapproach.springrest.cassandra.model.Buy;
import com.javasampleapproach.springrest.cassandra.repo.BuyRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class BuyController {

	@Autowired
	BuyRepository repository;

	@GetMapping("/buys")
	public List<Buy> getAll() {
		System.out.println("Get all Buys...");

		return repository.findAll();
	}

	@PostMapping("/buys/create")
	public ResponseEntity<Buy> postData(@RequestBody Buy data) {
		try {
			Buy _data = repository.save(
					new Buy(
							UUIDs.timeBased(), 
							data.getIduser(), 
							data.getIdshipper(), 
							data.getIdbook(), 
							data.getAmount(),
							data.getPrice(), 
							data.getAllprice()));
		    return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/buys/{id}")
	public ResponseEntity<String> deleteData(@PathVariable("id") UUID id) {
		System.out.println("Delete buy with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("buy has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/buys/delete")
	public ResponseEntity<String> deleteAllDatas() {
		System.out.println("Delete All buys...");

		repository.deleteAll();

		return new ResponseEntity<>("All buys have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/buys/id/{id}")
	public Optional<Buy> findById(@PathVariable UUID id) {

		Optional<Buy> datas = repository.findById(id);
		return datas;
	}
	@GetMapping("/buys/idshipper/{id}")
	public List<Buy> findByIdshipper(@PathVariable UUID id) {

		List<Buy> datas = repository.findByIdshipper(id);
		return datas;
	}

	@PutMapping("/buys/{id}")
	public ResponseEntity<Buy> updateData(@PathVariable("id") UUID id, @RequestBody Buy data) {
		System.out.println("Update buy with ID = " + id + "...");

		Optional<Buy> pData = repository.findById(id);

		if (pData.isPresent()) {
			Buy _data = pData.get();
			_data.setIduser(data.getIduser());
			_data.setIdshipper(data.getIdshipper());
			_data.setIdbook(data.getIdbook());
			_data.setAmount(data.getAmount());
			_data.setPrice(data.getPrice());
			_data.setAllprice(data.getAllprice());
			return new ResponseEntity<>(repository.save(_data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

