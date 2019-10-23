package com.alanlapierre.merchants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "sales")
public class Sale extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sal_id")
	private long id;
	
	@NotNull
	@Column(name = "sal_quantity", nullable = false)
	private Integer quantity;

	@Column(name = "sal_total_amount", nullable = false)
	private Double totalAmount;
	
	@Column(name = "sal_transaction_fee", nullable = false)
	private Double transactionFee;
	
	@ManyToOne
	@JoinColumn(name="pro_id", nullable = false)
	private Product product;
	
	@ManyToOne
    @JoinColumn(name = "mer_id", nullable = false)
    @JsonIgnore
	private Merchant merchant;
	
	
	public Sale() {
		super();
	}


	public Sale(long id, Integer quantity, Double totalAmount, Double transactionFee, Product product, Merchant merchant) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.transactionFee = transactionFee;
		this.product = product;
		this.merchant = merchant;
	}

	public Sale(Integer quantity, Double totalAmount, Double transactionFee, Product product, Merchant merchant) {
		super();
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.transactionFee = transactionFee;
		this.product = product;
		this.merchant = merchant;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(Double transactionFee) {
		this.transactionFee = transactionFee;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
