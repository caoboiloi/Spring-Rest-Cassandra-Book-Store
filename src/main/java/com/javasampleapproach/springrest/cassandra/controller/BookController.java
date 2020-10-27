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
import com.javasampleapproach.springrest.cassandra.model.Book;
import com.javasampleapproach.springrest.cassandra.repo.BookRepository;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository repository;

	@GetMapping("/books")
	public List<Book> getAllBooks() {
		System.out.println("Get all Books...");

		return repository.findAll();
	}

	@PostMapping("/books/create")
	public ResponseEntity<Book> postBook(@RequestBody Book book) {
		try {
			Book _book = repository.save(
					new Book(
							UUIDs.timeBased(), 
							book.getName(), 
							book.getPage(), 
							book.getPrice(),
							book.getSKU(),
							book.getPublisher(),
							book.getProvider(),
							book.getHeight(),
							book.getWidth(),
							book.getCover(),
							book.getImg(),
							book.getDescription(),
							book.getFeature(),
							book.getAuthor(),
							book.getBestselling(),
							book.getSale(),
							book.getBookn(),
							book.getFree()
							));
		    return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} 
		catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") UUID id) {
		System.out.println("Delete Book with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Book has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/books/delete")
	public ResponseEntity<String> deleteAllBooks() {
		System.out.println("Delete All Books...");

		repository.deleteAll();

		return new ResponseEntity<>("All Books have been deleted!", HttpStatus.OK);
	}

	@GetMapping("/books/sku/{sku}")
	public List<Book> findBySku(@PathVariable long sku) {

		List<Book> books = repository.findBySku(sku);
		return books;
	}
	
	@GetMapping("/books/feature/{feature}")
	public List<Book> findByFeature(@PathVariable int feature) {
		List<Book> books = repository.findByFeature(feature);
		return books;
	}
	
	@GetMapping("/books/publisher/{publisher}")
	public List<Book> findByPublisher(@PathVariable UUID publisher) {
		List<Book> books = repository.findByPublisher(publisher);
		return books;
	}
	
	@GetMapping("/books/provider/{provider}")
	public List<Book> findByProvider(@PathVariable UUID provider) {
		List<Book> books = repository.findByProvider(provider);
		return books;
	}
	
	@GetMapping("/books/id/{id}")
	public Optional<Book> findById(@PathVariable UUID id) {
		Optional<Book> books = repository.findById(id);
		return books;
	}
	
	@GetMapping("/books/best/{bestselling}")
	public List<Book> findByBestselling(@PathVariable int bestselling) {
		List<Book> books = repository.findByBestselling(bestselling);
		return books;
	}
	
	@GetMapping("/books/sale/{sale}")
	public List<Book> findBySale(@PathVariable int sale) {
		List<Book> books = repository.findBySale(sale);
		return books;
	}
	
	@GetMapping("/books/new/{newbook}")
	public List<Book> findByNewbook(@PathVariable int newbook) {
		List<Book> books = repository.findByNewbook(newbook);
		return books;
	}
	
	@GetMapping("/books/free/{free}")
	public List<Book> findByFree(@PathVariable int free) {
		List<Book> books = repository.findByFree(free);
		return books;
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") UUID id, @RequestBody Book book) {
		System.out.println("Update Book with ID = " + id + "...");

		Optional<Book> bookData = repository.findById(id);

		if (bookData.isPresent()) {
			Book _book = bookData.get();
			_book.setName(book.getName());
			_book.setPage(book.getPage());
			_book.setPrice(book.getPrice());
			_book.setCover(book.getCover());
			_book.setDescription(book.getDescription());
			_book.setHeight(book.getHeight());
			_book.setWidth(book.getWidth());
			_book.setImg(book.getImg());
			_book.setSKU(book.getSKU());
			_book.setPublisher(book.getPublisher());
			_book.setFeature(book.getFeature());
			_book.setProvider(book.getProvider());
			_book.setAuthor(book.getAuthor());
			_book.setBestselling(book.getBestselling());
			_book.setSale(book.getSale());
			_book.setBookn(book.getBookn());
			_book.setFree(book.getFree());
			return new ResponseEntity<>(repository.save(_book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
