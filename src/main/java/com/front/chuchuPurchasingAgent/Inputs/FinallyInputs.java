package com.front.chuchuPurchasingAgent.Inputs;

import java.util.List;

import com.front.chuchuPurchasingAgent.Models.QueryProduct;

public class FinallyInputs {
	private String email;
	private String code; 
	private String countryId;
	private List<QueryProduct> products;
	private int price;
	private String currencyId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public List<QueryProduct> getProducts() {
		return products;
	}
	public void setProducts(List<QueryProduct> products) {
		this.products = products;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}
