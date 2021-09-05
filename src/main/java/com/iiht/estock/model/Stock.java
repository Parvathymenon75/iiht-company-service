package com.iiht.estock.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "stocks")
public class Stock {

	@Id
	@ApiModelProperty(notes = "Database Generated Stock ID")
	private String id;

	@ApiModelProperty(notes = "Code for the company")
	private String companyCode;

	@ApiModelProperty(notes = "Current Date")
	private Date date;

	@ApiModelProperty(notes = "Price of the stock")
	@NotEmpty
	@NotBlank
	private String stockPrice;

	public Stock() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Stock(String id, String companyCode,Date date, String stockPrice) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.date = date;
		this.stockPrice = stockPrice;
	}


}
