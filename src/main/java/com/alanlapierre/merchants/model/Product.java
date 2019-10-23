package com.alanlapierre.merchants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "products")
public class Product extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_id")
	private long id;
	
	@Column(name = "pro_description", nullable = false)
	private String description;

	@Column(name = "pro_price", nullable = false)
	private Double price;
	
	
	public Product() {
		super();
	}

	public Product(long id, String description, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public Product(String description, Double price) {
		super();
		this.description = description;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
