package com.javasampleapproach.springrest.cassandra.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Buy {
	@PrimaryKey
	private UUID id;
	private UUID iduser;
	private UUID idshipper;
	private UUID idbook;
	private int amount;
	private float allprice;
	private float price;
	
	public Buy(){}
	public Buy(UUID id, UUID iduser, UUID idshipper, UUID idbook, int amount, float price, float allprice) {
		this.id = id;
		this.iduser = iduser;
		this.idshipper = idshipper;
		this.idbook = idbook;
		this.amount = amount;
		this.price = price;
		this.allprice = allprice;
	}
	
	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getIduser() {
		return this.iduser;
	}
	public void setIduser(UUID iduser) {
		this.iduser = iduser;
	}
	
	public UUID getIdshipper() {
		return this.idshipper;
	}
	public void setIdshipper(UUID idshipper) {
		this.idshipper = idshipper;
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
	
	public float getAllprice() {
		return this.allprice;
	}
	public void setAllprice(float allprice) {
		this.allprice = allprice;
	}
}
