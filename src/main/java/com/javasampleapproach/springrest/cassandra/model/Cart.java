package com.javasampleapproach.springrest.cassandra.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Cart {
	@PrimaryKey
	private UUID id;
	private UUID idshipper;
	private UUID iduser;
	private UUID idbook;
	private int amount;
	private float price;
	
	public Cart(){}
	public Cart(UUID id, UUID idshipper, UUID iduser, UUID idbook, int amount, float price) {
		this.id = id;
		this.idshipper = idshipper;
		this.iduser = iduser;
		this.idbook = idbook;
		this.amount = amount;
		this.price = price;
	}
	
	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getIdshipper() {
		return this.idshipper;
	}
	public void setIdshipper(UUID idshipper) {
		this.idshipper = idshipper;
	}
	
	public UUID getIduser() {
		return this.iduser;
	}
	public void setIduser(UUID iduser) {
		this.iduser = iduser;
	}
	
	public UUID getIdbook() {
		return this.idbook;
	}
	public void setIdbook(UUID idbook) {
		this.idbook = idbook;
	}
	
	public int getAmount() {
		return this.amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public float getPrice() {
		return this.price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
