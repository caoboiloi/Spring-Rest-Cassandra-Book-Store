package com.javasampleapproach.springrest.cassandra.repo;

import java.util.*;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.javasampleapproach.springrest.cassandra.model.Cart;
public interface CartRepository extends CassandraRepository<Cart, UUID> {
	@AllowFiltering
	List<Cart> findByAmount(int amount);
}
