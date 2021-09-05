package com.iiht.estock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.iiht.estock.model.Company;
import com.iiht.estock.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1.0/market/company")
@Api(value = "EstockMarketing Online store - Company")
public class CompanyController {

	@Autowired
	CompanyService service;

	@ApiOperation(value = "Get all Company")
	@GetMapping("/getall")
	public List<Company> fetchCompany() {
		return service.fetchCompanyList();
	}

	@ApiOperation(value = "Get a Company")
	@GetMapping("/info/{companyCode}")
	public ResponseEntity<Company> fetchByCompanyCode(@PathVariable String companyCode) {
		return service.getCompany(companyCode);
	}

	@ApiOperation(value = "Add a Company")
	@PostMapping("/register")
	public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company) {
		return service.addCompany(company);
	}

	@ApiOperation(value = "Delete a Company")
	@DeleteMapping("/delete/{companyCode}")
	public ResponseEntity<Company> deleteCompany(@PathVariable String companyCode) {
		return service.deleteCompany(companyCode);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
		 errors.put(error.getField(), error.getDefaultMessage()));
		 return errors;
	}
}
