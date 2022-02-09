package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.Query;

public class QueryBuilder {
	
	private Query query;
	
	public QueryBuilder() {
		this.query = new Query();
	}
	
	public QueryBuilder(Query query) {
		this.query = query;
	}
	public QueryBuilder buildId(String id) {
		this.query.setId(id);
		return this;
	}
	public QueryBuilder buildMemberId(String memberId) {
		this.query.setMemberId(memberId);
		return this;
	}
	public QueryBuilder buildEmail(String email) {
		this.query.setEmail(email);
		return this;
	}
	public QueryBuilder buildCountryId(String countryId) {
		this.query.setCountryId(countryId);;
		return this;
	}
	public QueryBuilder buildCurrencyId(String currencyId) {
		this.query.setCurrencyId(currencyId);;
		return this;
	}
	public QueryBuilder buildParcelRouteId(String parcelRouteId) {
		this.query.setParcelRouteId(parcelRouteId);
		return this;
	}
	public QueryBuilder buildStorehouseCountryId(String storehouseCountryId) {
		this.query.setStorehouseCountryId(storehouseCountryId);
		return this;
	}
	public QueryBuilder buildBankId(String bankId) {
		this.query.setBankId(bankId);
		return this;
	}
	public QueryBuilder buildDiscountCode(String discountCode) {
		this.query.setDiscountCode(discountCode);
		return this;
	}
	public QueryBuilder buildCreateTime(Timestamp createTime) {
		this.query.setCreateTime(createTime);
		return this;
	}
	public QueryBuilder buildCreator(String creator) {
		this.query.setCreator(creator);
		return this;
	}
	public QueryBuilder buildModifyTime(Timestamp modifyTime) {
		this.query.setModifyTime(modifyTime);
		return this;
	}
	public QueryBuilder buildModifier(String modifier) {
		this.query.setModifier(modifier);
		return this;
	}
	
	public Query build() {
		return this.query;
	}
}
