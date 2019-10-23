package com.alanlapierre.merchants.service;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.merchants.error.BusinessException;
import com.alanlapierre.merchants.model.Product;
import com.alanlapierre.merchants.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {

	Logger logger = LogManager.getLogger(ProductService.class);

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product findById(Long id) throws BusinessException {

		logger.info("findById, id: " + id);
		
		Product product = null;

		try {
			product = this.productRepository.findById(id).get();
		} catch (NoSuchElementException nse) {
			logger.error("findById: " + nse.toString());
			throw new BusinessException("Product not found");
		} catch (Exception e) {
			logger.error("findById: " + e.toString());
			throw new BusinessException("An error has occurred retrieving product information");
		}

		return product;

	}

}
