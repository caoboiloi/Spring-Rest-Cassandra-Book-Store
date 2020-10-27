package com.javasampleapproach.springrest.cassandra.model;

import java.util.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
@Table
public class Book {
	@PrimaryKey
	private UUID id;
	private String name;
	private int page;
	private float price;
	private long sku;
	private UUID publisher;
	private UUID provider;
	private float height;
	private float width;
	private String cover;
	private String img;
	private String description;
	private int feature;
	private String author;
	private int bestselling;
	private int sale;
	private int newbook;
	private int free;
	
	public Book() {
	}
	
	public Book(
			UUID id,
			String name, 
			int page, 
			float price, 
			long sku, 
			UUID publisher,
			UUID provider,
			float height, 
			float width, 
			String cover,
			String img,
			String description,
			int feature,
			String author,
			int bestselling,
			int sale,
			int newbook,
			int free
			) {
		this.id = id;
		this.name = name;
		this.page = page;
		this.price = price;
		this.sku = sku;
		this.publisher = publisher;
		this.provider = provider;
		this.height = height;
		this.width = width;
		this.cover = cover;
		this.img = img;
		this.description = description;
		this.feature = feature;
		this.author = author;
		this.bestselling = bestselling;
		this.sale = sale;
		this.newbook = newbook;
		this.free = free;
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
	
	public int getPage() {
		return this.page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public float getPrice() {
		return this.price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public long getSKU() {
		return this.sku;
	}
	public void setSKU(long sku) {
		this.sku = sku;
	}
	
	public UUID getPublisher() {
		return this.publisher;
	}
	public void setPublisher(UUID publisher) {
		this.publisher = publisher;
	}
	
	public float getHeight() {
		return this.height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getWidth() {
		return this.width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	public String getCover() {
		return this.cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getFeature() {
		return this.feature;
	}
	public void setFeature(int feature) {
		this.feature = feature;
	}
	
	public UUID getProvider() {
		return this.provider;
	}
	public void setProvider(UUID provider) {
		this.provider = provider;
	}
	
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getBestselling() {
		return this.bestselling;
	}
	public void setBestselling(int bestselling) {
		this.bestselling = bestselling;
	}
	
	public int getSale() {
		return this.sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	
	public int getBookn() {
		return this.newbook;
	}
	public void setBookn(int bookn) {
		this.newbook = bookn;
	}
	
	public int getFree() {
		return this.free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	
	@Override
	public String toString() {
		return String.format("book[id=%d,name=%s,price=%f,height=%f,width=%f]",id,name,price,height,width);
	}
}