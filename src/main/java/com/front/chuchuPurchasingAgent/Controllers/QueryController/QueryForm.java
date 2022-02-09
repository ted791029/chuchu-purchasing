package com.front.chuchuPurchasingAgent.Controllers.QueryController;

import java.util.List;

import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.Models.QueryProduct;

/**
 * 詢問表單
 * @author azooc
 *
 */
public class QueryForm implements Form{
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
	/**商品列**/
	private List<QueryProduct> products;
	
	
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
	
	public String getStorehouseCountryId() {
		return storehouseCountryId;
	}

	public void setStorehouseCountryId(String storehouseCountryId) {
		this.storehouseCountryId = storehouseCountryId;
	}

	public String getParcelRouteId() {
		return parcelRouteId;
	}

	public void setParcelRouteId(String parcelRouteId) {
		this.parcelRouteId = parcelRouteId;
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

	public List<QueryProduct> getProducts() {
		return products;
	}

	public void setProducts(List<QueryProduct> products) {
		this.products = products;
	}
	
}
