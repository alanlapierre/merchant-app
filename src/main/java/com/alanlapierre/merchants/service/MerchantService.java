package com.alanlapierre.merchants.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alanlapierre.merchants.error.BusinessException;
import com.alanlapierre.merchants.model.Merchant;
import com.alanlapierre.merchants.model.Product;
import com.alanlapierre.merchants.model.Sale;
import com.alanlapierre.merchants.repository.MerchantRepository;

@Service
@Transactional(readOnly = true)
public class MerchantService {

	Logger logger = LogManager.getLogger(MerchantService.class);

	private final MerchantRepository merchantRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private PlanService planService;

	public MerchantService(MerchantRepository merchantRepository) {
		this.merchantRepository = merchantRepository;
	}

	@Transactional
	public Merchant create(Merchant merchant) throws BusinessException {

		Merchant result = null;

		Long planId = merchant.getPlan().getId();
		this.planService.findById(planId);

		try {
			result = this.merchantRepository.save(merchant);
		} catch (Exception e) {
			throw new BusinessException("There was an error creating merchant");
		}

		return result;
	}

	@Transactional
	public Merchant update(Long merchantId, Merchant merchant) throws BusinessException {

		Merchant result = null;

		Long planId = merchant.getPlan().getId();
		this.planService.findById(planId);

		this.findById(merchantId);
		merchant.setId(merchantId);

		try {
			result = this.merchantRepository.save(merchant);
		} catch (Exception e) {
			throw new BusinessException("An error has occurred updating merchant information");
		}

		return result;
	}

	@Transactional
	public void delete(Long id) throws BusinessException {

		this.findById(id);

		try {
			this.merchantRepository.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException("An error has occurred deleting merchant information");
		}

	}

	public Merchant findById(Long id) throws BusinessException {

		Merchant merchant = null;

		try {
			merchant = this.merchantRepository.findById(id).get();
		} catch (NoSuchElementException nse) {
			throw new BusinessException("Merchant not found");
		} catch (Exception e) {
			throw new BusinessException("An error has occurred retrieving merchant information");
		}

		return merchant;
	}

	public List<Merchant> findAll() throws BusinessException {

		List<Merchant> merchants = null;

		try {
			merchants = this.merchantRepository.findAll();
		} catch (NoSuchElementException nse) {
			throw new BusinessException("Merchants not found");
		} catch (Exception e) {
			throw new BusinessException("An error has occurred retrieving list of merchants");
		}

		return merchants;
	}

	@Transactional
	public Merchant addSale(Long merchantId, Sale sale) throws BusinessException {

		Merchant savedMerchant = this.findById(merchantId);
		Product savedProduct = productService.findById(sale.getProduct().getId());

		Merchant result = null;

		try {
			Double totalAmount = savedProduct.getPrice() * sale.getQuantity();
			Double transactionFee = savedMerchant.getPlan().getTransactionFee() * totalAmount;

			// Valores para cada venta calculados.
			sale.setMerchant(savedMerchant);
			sale.setTotalAmount(totalAmount);
			sale.setTransactionFee(transactionFee);

			List<Sale> sales = new ArrayList<Sale>();
			sales.add(sale);
			savedMerchant.setSales(sales);
			result = this.update(merchantId, savedMerchant);
		} catch (Exception e) {
			throw new BusinessException("An error occurred while adding a new sale");
		}

		return result;

	}

	public Double getAllTransactionFees(Long merchantId) throws BusinessException {

		Merchant savedMerchant = this.findById(merchantId);

		List<Sale> sales = savedMerchant.getSales();

		Double totalFees = 0D;

		for (Sale sale : sales) {
			totalFees += sale.getTransactionFee();
		}

		return totalFees;

	}

}
