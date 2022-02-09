package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.SettingRate;

public class SettingRateBuilder {
	
	private SettingRate settingRate;
	
	public SettingRateBuilder() {
		this.settingRate = new SettingRate();
	}
	public SettingRateBuilder(SettingRate settingRate) {
		this.settingRate = settingRate;
	}
	
	public SettingRateBuilder buildId(String id) {
		this.settingRate.setId(id);
		return this;
	}
	
	public SettingRateBuilder buildWhereId(String whereId) {
		this.settingRate.setWhereId(whereId);
		return this;
	}
	
	public SettingRateBuilder buildCountryId(String countryId) {
		this.settingRate.setCountryId(countryId);
		return this;
	}
	
	public SettingRateBuilder buildCustomerTypeId(String customerTypeId) {
		this.settingRate.setCustomerTypeId(customerTypeId);
		return this;
	}
	
	public SettingRateBuilder buildRate(double rate) {
		this.settingRate.setRate(rate);
		return this;
	}
	
	public SettingRateBuilder buildCreateTime(Timestamp createTime) {
		this.settingRate.setCreateTime(createTime);
		return this;
	}
	public SettingRateBuilder buildCreator(String creator) {
		this.settingRate.setCreator(creator);
		return this;
	}
	public SettingRateBuilder buildModifyTime(Timestamp modifyTime) {
		this.settingRate.setModifyTime(modifyTime);
		return this;
	}
	public SettingRateBuilder buildModifier(String modifier) {
		this.settingRate.setModifier(modifier);
		return this;
	}
	
	public SettingRate build() {
		return this.settingRate;
	}

}
