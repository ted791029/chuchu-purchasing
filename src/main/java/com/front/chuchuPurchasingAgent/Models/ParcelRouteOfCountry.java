package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_COUNTRY_PARCEL_ROUTE")
public class ParcelRouteOfCountry {
	/**唯一碼**/
	@Id
	private String id;
	/**國家ID**/
	@Column(name = "COUNTRY_ID", nullable = false)
	private String countryId;
	/**運送方式ID**/
	@Column(name = "ROUTE_ID", nullable = false)
	private String routeId;
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
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
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
