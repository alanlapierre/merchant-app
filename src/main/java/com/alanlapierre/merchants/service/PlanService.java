package com.alanlapierre.merchants.service;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.merchants.error.BusinessException;
import com.alanlapierre.merchants.model.Plan;
import com.alanlapierre.merchants.repository.PlanRepository;

@Service
@Transactional(readOnly = true)
public class PlanService {

	Logger logger = LogManager.getLogger(PlanService.class);

	private final PlanRepository planRepository;

	public PlanService(PlanRepository planRepository) {
		this.planRepository = planRepository;
	}

	public Plan findById(Long id) throws BusinessException {

		logger.info("findById, id: " + id);
		
		Plan plan = null;

		try {
			plan = this.planRepository.findById(id).get();
		} catch (NoSuchElementException nse) {
			logger.error("findById: " + nse.toString());
			throw new BusinessException("Plan not found");
		} catch (Exception e) {
			logger.error("findById: " + e.toString());
			throw new BusinessException("An error has occurred retrieving plan information");
		}

		return plan;

	}

}
