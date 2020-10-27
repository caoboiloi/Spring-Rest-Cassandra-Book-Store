package com.javasampleapproach.springrest.cassandra.repo;

import java.util.*;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.javasampleapproach.springrest.cassandra.model.User;
public interface UserRepository extends CassandraRepository<User, UUID> {
	@AllowFiltering
	List<User> findByUsername(String user);
	@AllowFiltering
	List<User> findBySex(String sex);
}
