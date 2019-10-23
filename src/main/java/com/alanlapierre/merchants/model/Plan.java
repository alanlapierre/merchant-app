package com.alanlapierre.merchants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "plans")
public class Plan extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pla_id")
	@NotNull
	private long id;
	
	@Column(name = "pla_description", nullable = false)
	private String description;

	@Column(name = "pla_transaction_fee", nullable = false)
	private Double transactionFee;
	
	
	public Plan() {
		super();
	}

	
	public Plan(long id, String description, Double transactionFee) {
		super();
		this.id = id;
		this.description = description;
		this.transactionFee = transactionFee;
	}


	public Plan(String description, Double transactionFee) {
		super();
		this.description = description;
		this.transactionFee = transactionFee;
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

	public Double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(Double transactionFee) {
		this.transactionFee = transactionFee;
	}

}
