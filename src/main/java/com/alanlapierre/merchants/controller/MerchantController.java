package com.alanlapierre.merchants.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanlapierre.merchants.error.BusinessException;
import com.alanlapierre.merchants.model.Merchant;
import com.alanlapierre.merchants.model.Sale;
import com.alanlapierre.merchants.service.MerchantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "merchant")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@GetMapping("/merchants")
	@ApiOperation(value = "Get all merchants", notes = "Service to list all merchants")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Merchants found"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> getAllMerchants() {

		try {
			return new ResponseEntity<>(merchantService.findAll(), HttpStatus.OK);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/merchants/{id}")
	@ApiOperation(value = "Get merchant", notes = "Service to get merchant by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Merchant found"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> getMerchant(@PathVariable Long id) {

		try {
			return new ResponseEntity<>(merchantService.findById(id), HttpStatus.OK);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/merchants/{id}")
	@ApiOperation(value = "Delete Merchant", notes = "Service to delete a merchant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Merchant deleted successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> deleteMerchant(@PathVariable Long id) {

		try {
			this.merchantService.delete(id);
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/merchants")
	@ApiOperation(value = "Create Merchant", notes = "Service to add a new merchant")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Merchant created successfully"),
			@ApiResponse(code = 400, message = "Invalid Request") })
	public ResponseEntity<Object> createMerchant(@Valid @RequestBody Merchant merchant) {

		try {
			return new ResponseEntity<>(this.merchantService.create(merchant), HttpStatus.CREATED);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/merchants/{id}")
	@ApiOperation(value = "Update Merchant", notes = "Service to update an existing merchant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Merchant updated successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> updateMerchant(@PathVariable Long id, @Valid @RequestBody Merchant merchant) {

		try {
			return new ResponseEntity<>(this.merchantService.update(id, merchant), HttpStatus.OK);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/merchants/{id}/sales")
	@ApiOperation(value = "Add new sale to the merchant", notes = "Service to add new sale to the merchant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "New sale added successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> addSale(@PathVariable Long id, @Valid @RequestBody Sale sale) {

		try {
			return new ResponseEntity<>(this.merchantService.addSale(id, sale), HttpStatus.OK);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/merchants/{id}/sales/transactionfees")
	@ApiOperation(value = "Calculate the total of transaction fees", notes = "Service to calculate the total of transaction fees")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Total of transaction fees calculated successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
	public ResponseEntity<Object> getAllTransactionFees(@PathVariable Long id) {

		try {
			return new ResponseEntity<>(merchantService.getAllTransactionFees(id), HttpStatus.OK);
		} catch (BusinessException be) {
			return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
