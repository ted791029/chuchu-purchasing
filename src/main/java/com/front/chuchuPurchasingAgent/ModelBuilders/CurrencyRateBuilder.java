package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.CurrencyRate;

public class CurrencyRateBuilder {
	
	private CurrencyRate currencyRate;
	
	public CurrencyRateBuilder() {
		this.currencyRate = new CurrencyRate();
	}
	public CurrencyRateBuilder(CurrencyRate currencyRate) {
		this.currencyRate = currencyRate;
	}
	
	public CurrencyRateBuilder buildId(String id) {
		this.currencyRate.setId(id);
		return this;
	}
	public CurrencyRateBuilder buildCurrencyId(String currencyId) {
		this.currencyRate.setCurrencyId(currencyId);;
		return this;
	}
	public CurrencyRateBuilder buildStatus(double rate) {
		this.currencyRate.setRate(rate);
		return this;
	}
	public CurrencyRateBuilder buildCreateTime(Timestamp createTime) {
		this.currencyRate.setCreateTime(createTime);
		return this;
	}
	public CurrencyRateBuilder buildCreator(String creator) {
		this.currencyRate.setCreator(creator);
		return this;
	}
	public CurrencyRateBuilder buildModifyTime(Timestamp modifyTime) {
		this.currencyRate.setModifyTime(modifyTime);
		return this;
	}
	public CurrencyRateBuilder buildModifier(String modifier) {
		this.currencyRate.setModifier(modifier);
		return this;
	}
	public CurrencyRate build() {
		return this.currencyRate;
	}
}
