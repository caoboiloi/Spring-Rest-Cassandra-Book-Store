package com.javasampleapproach.springrest.cassandra.repo;

import java.util.*;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.javasampleapproach.springrest.cassandra.model.Buy;
public interface BuyRepository extends CassandraRepository<Buy, UUID> {
	@AllowFiltering
	List<Buy> findByIdshipper(UUID id);
}
