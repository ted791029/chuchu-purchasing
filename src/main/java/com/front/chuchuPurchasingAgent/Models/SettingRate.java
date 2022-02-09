package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_SETTING_RATE")
public class SettingRate {
	/**唯一碼**/
	@Id
	private String id;
	/**來源ID**/
	@Column(name = "WHERE_ID", nullable = false)
	private String whereId ;
	/**國家ID**/
	@Column(name = "COUNTRY_ID", nullable = false)
	private String countryId;
	/**顧客型態ID**/
	@Column(name = "CUSTOMER_TYPE_ID", nullable = false)
	private String customerTypeId;
	/**匯率**/
	private Double rate;
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

}
