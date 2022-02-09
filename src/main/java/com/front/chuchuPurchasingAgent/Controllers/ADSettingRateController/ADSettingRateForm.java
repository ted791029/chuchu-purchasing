package com.front.chuchuPurchasingAgent.Controllers.ADSettingRateController;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Controllers.Form;

public class ADSettingRateForm implements Form{
	
	private String id;
	
	private String whereId ;

	private String countryId;

	private String customerTypeId;

	private Double rate;

	private Timestamp createTime;

	private String creator;

	private Timestamp modifyTime;
	
	private String modifier;
	
	private String urlSuffix;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWhereId() {
		return whereId;
	}

	public void setWhereId(String whereId) {
		this.whereId = whereId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getUrlSuffix() {
		return urlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.urlSuffix = urlSuffix;
	}

}
