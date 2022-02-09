package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.PurchasingRate;

public class PurchasingRateBuilder {
	
	private PurchasingRate purchasingRate;
	
	public PurchasingRateBuilder() {
		this.purchasingRate = new PurchasingRate();
	}
	
	public PurchasingRateBuilder(PurchasingRate purchasingRate) {
		this.purchasingRate = purchasingRate;
	}	
	public PurchasingRateBuilder buildId(String id) {
		this.purchasingRate.setId(id);
		return this;
	}	
	public PurchasingRateBuilder buildWhereId(String whereId) {
		this.purchasingRate.setWhereId(whereId);
		return this;
	}	
	public PurchasingRateBuilder buildCountryId(String countryId) {
		this.purchasingRate.setCountryId(countryId);
		return this;
	}	
	public PurchasingRateBuilder buildRate(Double rate) {
		this.purchasingRate.setRate(rate);
		return this;
	}
	public PurchasingRateBuilder buildCreateTime(Timestamp createTime) {
		this.purchasingRate.setCreateTime(createTime);
		return this;
	}
	public PurchasingRateBuilder buildCreator(String creator) {
		this.purchasingRate.setCreator(creator);
		return this;
	}
	public PurchasingRateBuilder buildModifyTime(Timestamp modifyTime) {
		this.purchasingRate.setModifyTime(modifyTime);
		return this;
	}
	public PurchasingRateBuilder buildModifier(String modifier) {
		this.purchasingRate.setModifier(modifier);
		return this;
	}
	public PurchasingRate build() {
		return this.purchasingRate;
	}

}
