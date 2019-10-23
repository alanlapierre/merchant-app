package com.alanlapierre.merchants.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "merchants")
public class Merchant extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mer_id")
	private long id;
	
	@NotNull
	@Column(name = "mer_email", nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(name = "mer_phone", nullable = false, unique = true)
	private String phone;
	
	@NotNull
	@Column(name = "mer_name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "mer_address", nullable = false)
	private String address;

	@NotNull
	@ManyToOne
	@JoinColumn(name="pla_id", nullable = false)
	private Plan plan;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
    private List<Sale> sales;
	
	public Merchant() {
		super();
	}


	public Merchant(long id, String email, String phone, String name, String address, Plan plan, List<Sale> sales) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.plan = plan;
		this.sales = sales;
	}



	public Merchant(String email, String phone, String name, String address, Plan plan, List<Sale> sales) {
		super();
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.plan = plan;
		this.sales = sales;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}


	public List<Sale> getSales() {
		return sales;
	}


	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
}
