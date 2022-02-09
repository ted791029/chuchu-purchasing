package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_PARCEL_ROUTE_NOTE")
public class ParcelRouteNote {
	/**唯一碼**/
	@Id
	private String id;
	/**國家運送路線**/
	@Column(name = "COUNTRY_PARCEL_ROUTE_ID", nullable = false)
	private String countryParcelRouteId;
	/**來源**/
	@Column(name = "FROM_WHERE_ID", nullable = false)
	private String fromWhereId;
	/**內容**/
	private String content;
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
	public String getCountryParcelRouteId() {
		return countryParcelRouteId;
	}
	public void setCountryParcelRouteId(String countryParcelRouteId) {
		this.countryParcelRouteId = countryParcelRouteId;
	}
	public String getFromWhereId() {
		return fromWhereId;
	}
	public void setFromWhereId(String fromWhereId) {
		this.fromWhereId = fromWhereId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
