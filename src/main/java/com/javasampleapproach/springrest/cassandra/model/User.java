package com.javasampleapproach.springrest.cassandra.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class User {
	@PrimaryKey
	private UUID id;
	private String img;
	private String username;
	private String pass;
	private int role;
	private int age;
	private String name;
	private String address;
	private String sex;
	
	public User(){}
	public User(UUID id, String username, String pass, int role, String name, String address, String sex, int age, String img) {
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.role = role;
		this.address = address;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.img = img;
	}
	
	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
