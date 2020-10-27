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
import com.javasampleapproach.springrest.cassandra.model.Cart;
import com.javasampleapproach.springrest.cassandra.repo.CartRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	CartRepository repository;

	@GetMapping("/carts")
	public List<Cart> getAll() {
		System.out.println("Get all carts...");

		return repository.findAll();
	}

	@PostMapping("/carts/create")
	public ResponseEntity<Cart> postData(@RequestBody Cart data) {
		try {
			Cart _data = repository.save(
					new Cart(
							UUIDs.timeBased(), 
							data.getIdshipper(), 
							data.getIduser(), 
							data.getIdbook(),
							data.getAmount(), 
							data.getPrice()));
		    return new ResponseEntity<>(_data, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/carts/{id}")
	public ResponseEntity<String> deleteData(@PathVariable("id") UUID id) {
		System.out.println("Delete cart with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("cart has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/carts/delete")
	public ResponseEntity<String> deleteAllDatas() {
		System.out.println("Delete All carts...");

		repository.deleteAll();

		return new ResponseEntity<>("All carts have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/carts/id/{id}")
	public Optional<Cart> findById(@PathVariable UUID id) {

		Optional<Cart> datas = repository.findById(id);
		return datas;
	}
	
	@GetMapping("/carts/amount/{amount}")
	public List<Cart> findByAmount(@PathVariable int amount) {

		List<Cart> datas = repository.findByAmount(amount);
		return datas;
	}

	@PutMapping("/carts/{id}")
	public ResponseEntity<Cart> updateData(@PathVariable("id") UUID id, @RequestBody Cart data) {
		System.out.println("Update Publisher with ID = " + id + "...");

		Optional<Cart> pData = repository.findById(id);

		if (pData.isPresent()) {
			Cart _data = pData.get();
			_data.setIdshipper(data.getIdshipper());
			_data.setIduser(data.getIduser());
			_data.setIdbook(data.getIdbook());
			_data.setAmount(data.getAmount());
			_data.setPrice(data.getPrice());
			return new ResponseEntity<>(repository.save(_data), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

