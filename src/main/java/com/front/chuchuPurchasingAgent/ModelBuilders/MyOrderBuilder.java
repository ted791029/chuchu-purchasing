package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.MyOrder;

public class MyOrderBuilder {
	
private MyOrder order;
	
	public MyOrderBuilder() {
		this.order = new MyOrder();
	}
	
	public MyOrderBuilder(MyOrder order) {
		this.order = order;
	}
	public MyOrderBuilder buildId(String id) {
		this.order.setId(id);
		return this;
	}
	public MyOrderBuilder buildMemberId(String memberId) {
		this.order.setMemberId(memberId);
		return this;
	}
	public MyOrderBuilder buildEmail(String email) {
		this.order.setEmail(email);
		return this;
	}
	public MyOrderBuilder buildCountryId(String countryId) {
		this.order.setCountryId(countryId);;
		return this;
	}
	public MyOrderBuilder buildCurrencyId(String currencyId) {
		this.order.setCurrencyId(currencyId);;
		return this;
	}
	public MyOrderBuilder buildParcelRouteId(String parcelRouteId) {
		this.order.setParcelRouteId(parcelRouteId);
		return this;
	}
	public MyOrderBuilder buildStorehouseCountryId(String storehouseCountryId) {
		this.order.setStorehouseCountryId(storehouseCountryId);
		return this;
	}
	public MyOrderBuilder buildBankId(String bankId) {
		this.order.setBankId(bankId);
		return this;
	}
	public MyOrderBuilder buildDiscountCode(String discountCode) {
		this.order.setDiscountCode(discountCode);
		return this;
	}
	public MyOrderBuilder buildStatusId(String statusId) {
		this.order.setStatusId(statusId);
		return this;
	}
	public MyOrderBuilder buildAllTotalPrice(int allTotalPrice) {
		this.order.setAllTotalPrice(allTotalPrice);
		return this;
	}
	public MyOrderBuilder buildPurchasingPrice(int purchasingPrice) {
		this.order.setPurchasingPrice(purchasingPrice);
		return this;
	}
	public MyOrderBuilder buildLocalFeePrice(int localFeePrice) {
		this.order.setLocalFeePrice(localFeePrice);
		return this;
	}
	public MyOrderBuilder buildFirstStagePrice(int firstStagePrice) {
		this.order.setLocalFeePrice(firstStagePrice);
		return this;
	}
	public MyOrderBuilder buildCreateTime(Timestamp createTime) {
		this.order.setCreateTime(createTime);
		return this;
	}
	public MyOrderBuilder buildCreator(String creator) {
		this.order.setCreator(creator);
		return this;
	}
	public MyOrderBuilder buildModifyTime(Timestamp modifyTime) {
		this.order.setModifyTime(modifyTime);
		return this;
	}
	public MyOrderBuilder buildModifier(String modifier) {
		this.order.setModifier(modifier);
		return this;
	}
	
	public MyOrder build() {
		return this.order;
	}
}
