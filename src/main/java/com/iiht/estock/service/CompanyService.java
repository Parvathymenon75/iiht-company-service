
package com.iiht.estock.service;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iiht.estock.Repository.CompanyRepository;
import com.iiht.estock.exception.DuplicateException;
import com.iiht.estock.exception.ResourceNotFoundException;
import com.iiht.estock.model.Company;
import com.iiht.estock.model.Stock;

@Service
public class CompanyService {

	private static final Logger LOG = Logger.getLogger(CompanyService.class.getName());

	@Autowired
	CompanyRepository repo;

	/*
	 * @Autowired KafkaSender kafkaSender;
	 */

	@Value("${retrieveStockUrl}")
	private String retrieveStockUrl;

	@Value("${deleteStockUrl}")
	private String deleteStockUrl;

	public List<Company> fetchCompanyList() {

		List<Company> companyList = repo.findAll();
		LOG.log(Level.INFO, companyList.toString());
		for (Company company : companyList) {
			retrieveStock(company);
		}
		//kafkaSender.send(companyList.toString());
		LOG.log(Level.INFO, "Get All companiess-----" + companyList.toString());
		return companyList;
	}

	public ResponseEntity<Company> addCompany(Company company) {

		if (repo.findByCompanyCode(company.getCompanyCode()) != null) {
			LOG.log(Level.ERROR, "Company Already Exists");
			throw new DuplicateException("Company Already Exists");
		}
		Company companySaved = repo.save(company);
		LOG.log(Level.INFO, companySaved.getId());
		//kafkaSender.send(company.getId());
		return new ResponseEntity<Company>(companySaved, HttpStatus.OK);
	}

	public ResponseEntity<Company> getCompany(String companyCode) {
		if (repo.findByCompanyCode(companyCode) == null) {
			LOG.log(Level.ERROR, "Company Not Found");
			throw new ResourceNotFoundException("Company not found");
		}

		Company company = repo.findByCompanyCode(companyCode);
		retrieveStock(company);
		LOG.log(Level.INFO, "GET COMPANY------" + company.getId());
		// kafkaSender.send(company.getId());
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	public ResponseEntity<Company> deleteCompany(String companyCode) {
		Company company = repo.findByCompanyCode(companyCode);
		if (company == null) {
			LOG.log(Level.ERROR, "Company Not Found");
			throw new ResourceNotFoundException("Company not found");
		}

		RestTemplate restTemplate = new RestTemplate();
		StringBuilder sb = new StringBuilder();
		sb.append(deleteStockUrl).append(company.getCompanyCode());

		restTemplate.delete(sb.toString());

		repo.deleteById(company.getId());
		LOG.log(Level.INFO, "Deleted Company By Company Code: " + company.getId());
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}

	public void retrieveStock(Company company) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder sb = new StringBuilder();
		sb.append(retrieveStockUrl).append(company.getCompanyCode());
		Stock[] stockList = restTemplate.getForObject(sb.toString(), Stock[].class);

		if (stockList != null) {
			company.setStockList(stockList);
		}
	}

}
