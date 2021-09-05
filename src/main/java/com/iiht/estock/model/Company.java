package com.iiht.estock.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "companies")
public class Company {

	@Id
	@ApiModelProperty(notes = "Database Generated Company ID")
	private String id;

	@ApiModelProperty(notes = "Code for the company")
	@NotEmpty
	@NotBlank
	private String companyCode;

	@ApiModelProperty(notes = "Name of the company")
	@NotEmpty
	@NotBlank
	private String companyName;

	@ApiModelProperty(notes = "CEO of the company")
	@NotEmpty
	@NotBlank
	private String companyCeo;

	@ApiModelProperty(notes = "Website Url of the company")
	@NotEmpty
	@NotBlank
	private String companyWebsite;

	@ApiModelProperty(notes = "Turnover of the company")
	@Min(value = 100000000, message = "Company Turnover Should be greater than 10 crores")
	@NotEmpty
	@NotBlank
	private String companyTurnover;

	@ApiModelProperty(notes = "List of the stock")
	@Transient
	private Stock[] stockList;

	@ApiModelProperty(notes = "StockExchange Details of the company")
	@NotEmpty
	@NotBlank
	private String stockExchange;

	public Company() {

	}

	public Company(String id, String companyCode, String companyName, String companyCeo, String companyWebsite,
			String companyTurnover, Stock[] stockList, String stockExchange) {
		this.id = id;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCeo = companyCeo;
		this.companyWebsite = companyWebsite;
		this.companyTurnover = companyTurnover;
		this.stockList = stockList;
		this.stockExchange = stockExchange;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCeo() {
		return companyCeo;
	}

	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyTurnover() {
		return companyTurnover;
	}

	public void setCompanyTurnover(String companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Stock[] getStockList() {
		return stockList;
	}

	public void setStockList(Stock[] stockList) {
		this.stockList = stockList;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
}
