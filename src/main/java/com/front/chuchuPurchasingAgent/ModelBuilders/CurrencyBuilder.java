package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.Currency;

public class CurrencyBuilder {
	
	private Currency currency;
	
	public CurrencyBuilder() {
		this.currency = new Currency();
	}
	
	public CurrencyBuilder(Currency currency) {
		this.currency = currency;
	}
	
	public CurrencyBuilder buildId(String id) {
		this.currency.setId(id);
		return this;
	}
	public CurrencyBuilder buildAreaId(String areaId) {
		this.currency.setAreaId(areaId);
		return this;
	}
	public CurrencyBuilder buildName(String name) {
		this.currency.setName(name);
		return this;
	}
	public CurrencyBuilder buildStatus(String status) {
		this.currency.setStatus(status);
		return this;
	}
	public CurrencyBuilder buildCreateTime(Timestamp createTime) {
		this.currency.setCreateTime(createTime);
		return this;
	}
	public CurrencyBuilder buildCreator(String creator) {
		this.currency.setCreator(creator);
		return this;
	}
	public CurrencyBuilder buildModifyTime(Timestamp modifyTime) {
		this.currency.setModifyTime(modifyTime);
		return this;
	}
	public CurrencyBuilder buildModifier(String modifier) {
		this.currency.setModifier(modifier);
		return this;
	}
	public Currency build() {
		return this.currency;
	}
}
