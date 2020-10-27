package com.javasampleapproach.springrest.cassandra.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Admin {
	@PrimaryKey
	private UUID id;
	private String name;
	private String username;
	private String pass;
	private int role;
	private int access;
	
	public Admin(){}
	public Admin(UUID id, String name, String username, String pass, int role, int access) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.pass = pass;
		this.role = role;
		this.access = access;
	}
	
	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPass() {
		return this.pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public int getRole() {
		return this.role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public int getAccess() {
		return this.access;
	}
	public void setAccess(int access) {
		this.access = access;
	}
}
