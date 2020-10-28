package com.javasampleapproach.springrest.cassandra.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Contact {
	@PrimaryKey
	private UUID id;
	private String name;
	private String mail;
	private String message;
	
	
	public Contact(){}
	public Contact(UUID id,String name, String mail,String message) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.message = message;
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
	
	public String getMail() {
		return this.mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
