package com.javasampleapproach.springrest.cassandra.repo;

import java.util.*;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.web.bind.annotation.PathVariable;

import com.javasampleapproach.springrest.cassandra.model.Book;
public interface BookRepository extends CassandraRepository<Book, UUID> {
	@AllowFiltering
	List<Book> findBySku(long sku);
	@AllowFiltering
	List<Book> findByFeature(int feature);
	@AllowFiltering
	List<Book> findByPublisher(UUID publisher);
	@AllowFiltering
	List<Book> findByProvider(UUID provider);
	@AllowFiltering
	List<Book> findByBestselling(int bestselling);
	@AllowFiltering
	List<Book> findBySale(int sale);
	@AllowFiltering
	List<Book> findByNewbook(int newbook);
	@AllowFiltering
	List<Book> findByFree(int free);
}
