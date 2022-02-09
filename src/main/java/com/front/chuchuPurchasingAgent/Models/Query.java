package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_QUERY")
public class Query {
	/**唯一碼**/
	@Id
	private String id;
	/**會員唯一碼**/
	@Column(name = "MEMBER_ID")
	private String memberId;
	/**信箱**/
	private String email;
	/**國家ID**/
	@Column(name = "COUNTRY_ID")
	private String countryId;
	/**幣別ID**/
	@Column(name = "CURRENCY_ID")
	private String currencyId;
	/**包裹航線ID**/
	@Column(name = "PARCEL_ROUTE_ID")
	private String parcelRouteId;
	/**集貨國家ID**/
	@Column(name = "STOREHOUSE_COUNTRY_ID")
	private String storehouseCountryId;
	/**銀行**/
	@Column(name = "BANK_ID")
	private String bankId;
	/**折扣碼**/
	@Column(name = "DISCOUNT_CODE")
	private String discountCode;
	/**創造日期**/
	@Column(name = "CREATE_TIME", nullable = false)
	private Timestamp createTime;
	/**創造者**/
	private String creator;
	/**異動日期**/
	@Column(name = "MODIFY_TIME", nullable = false)
	private Timestamp modifyTime;
	/**異動者**/
	private String modifier;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
}
