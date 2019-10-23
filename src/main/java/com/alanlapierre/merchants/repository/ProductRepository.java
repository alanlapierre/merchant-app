package com.alanlapierre.merchants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.merchants.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
