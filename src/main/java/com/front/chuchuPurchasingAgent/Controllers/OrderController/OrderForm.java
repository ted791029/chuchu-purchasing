package com.front.chuchuPurchasingAgent.Controllers.OrderController;

import java.util.List;

import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.Models.OrderProduct;

public class OrderForm implements Form{
	
	/**編號**/
	private String id;
	/**信箱**/
	private String email;
	/**地區ID**/
	private String countryAreaId;
	/**國家ID**/
	private String countryId;
	/**幣別ID**/
	private String currencyId;
	/**包裹航線ID**/
	private String parcelRouteId;
	/**集貨國家ID**/
	private String storehouseCountryId;
	/**銀行**/
	private String bankId;
	/**折扣碼**/
	private String discountCode;
	/**狀態**/
	private String status;
	/**商品合計**/
	private int allTotalPrice;
	/**代買費用**/
	private int purchasingPrice;
	/**當地手續費**/
	private int localFeePrice;
	/**第一階段費用**/
	private int firstStagePrice;
	/**商品列**/
	private List<OrderProduct> products;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryAreaId() {
		return countryAreaId;
	}
	public void setCountryAreaId(String countryAreaId) {
		this.countryAreaId = countryAreaId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getParcelRouteId() {
		return parcelRouteId;
	}
	public void setParcelRouteId(String parcelRouteId) {
		this.parcelRouteId = parcelRouteId;
	}
	
	public String getStorehouseCountryId() {
		return storehouseCountryId;
	}
	public void setStorehouseCountryId(String storehouseCountryId) {
		this.storehouseCountryId = storehouseCountryId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAllTotalPrice() {
		return allTotalPrice;
	}
	public void setAllTotalPrice(int allTotalPrice) {
		this.allTotalPrice = allTotalPrice;
	}
	public int getPurchasingPrice() {
		return purchasingPrice;
	}
	public void setPurchasingPrice(int purchasingPrice) {
		this.purchasingPrice = purchasingPrice;
	}
	public int getLocalFeePrice() {
		return localFeePrice;
	}
	public void setLocalFeePrice(int localFeePrice) {
		this.localFeePrice = localFeePrice;
	}
	public int getFirstStagePrice() {
		return firstStagePrice;
	}
	public void setFirstStagePrice(int firstStagePrice) {
		this.firstStagePrice = firstStagePrice;
	}
	public List<OrderProduct> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}
}
