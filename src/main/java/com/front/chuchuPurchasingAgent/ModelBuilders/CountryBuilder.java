package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.Country;

public class CountryBuilder {
	private Country country;
	
	public CountryBuilder() {
		this.country = new Country();
	}
	
	public CountryBuilder(Country country) {
		this.country = country;
	}
	
	public CountryBuilder buildId(String id) {
		this.country.setId(id);
		return this;
	}
	public CountryBuilder buildAreaId(String areaId) {
		this.country.setAreaId(areaId);
		return this;
	}
	public CountryBuilder buildName(String name) {
		this.country.setName(name);
		return this;
	}
	public CountryBuilder buildStorehouseStatus(String storehouseStatus) {
		this.country.setStorehouseStatus(storehouseStatus);
		return this;
	}
	public CountryBuilder buildWebStatus(String webStatus) {
		this.country.setWebStatus(webStatus);
		return this;
	}
	public CountryBuilder buildCreateTime(Timestamp createTime) {
		this.country.setCreateTime(createTime);
		return this;
	}
	public CountryBuilder buildCreator(String creator) {
		this.country.setCreator(creator);
		return this;
	}
	public CountryBuilder buildModifyTime(Timestamp modifyTime) {
		this.country.setModifyTime(modifyTime);
		return this;
	}
	public CountryBuilder buildModifier(String modifier) {
		this.country.setModifier(modifier);
		return this;
	}
	public Country build() {
		return this.country;
	}

}
