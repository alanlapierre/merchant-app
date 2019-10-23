package com.alanlapierre.merchants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alanlapierre.merchants.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
