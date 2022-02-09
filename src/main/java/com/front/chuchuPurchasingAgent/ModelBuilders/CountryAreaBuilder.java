package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.CountryArea;

public class CountryAreaBuilder{
	private CountryArea countryArea;
	
	public CountryAreaBuilder() {
		this.countryArea = new CountryArea();
	}
	public CountryAreaBuilder(CountryArea countryArea) {
		this.countryArea = countryArea;
	}
	
	public CountryAreaBuilder buildId(String id) {
		this.countryArea.setId(id);
		return this;
	}
	public CountryAreaBuilder buildName(String name) {
		this.countryArea.setName(name);
		return this;
	}
	public CountryAreaBuilder buildStatus(String status) {
		this.countryArea.setStatus(status);
		return this;
	}
	public CountryAreaBuilder buildCreateTime(Timestamp createTime) {
		this.countryArea.setCreateTime(createTime);
		return this;
	}
	public CountryAreaBuilder buildCreator(String creator) {
		this.countryArea.setCreator(creator);
		return this;
	}
	public CountryAreaBuilder buildModifyTime(Timestamp modifyTime) {
		this.countryArea.setModifyTime(modifyTime);
		return this;
	}
	public CountryAreaBuilder buildModifier(String modifier) {
		this.countryArea.setModifier(modifier);
		return this;
	}
	public CountryArea build() {
		return this.countryArea;
	}

}
