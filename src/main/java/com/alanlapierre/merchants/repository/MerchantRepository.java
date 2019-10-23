package com.alanlapierre.merchants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.merchants.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
