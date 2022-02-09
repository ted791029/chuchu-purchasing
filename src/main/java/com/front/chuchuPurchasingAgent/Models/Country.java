package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_COUNTRY")
public class Country {
	/**唯一碼**/
	@Id
	private String id;
	@Column(name = "AREA_ID", nullable = false)
	private String areaId;
	/**名稱**/
	private String name;
	/**網站狀態**/
	@Column(name = "WEB_STATUS", nullable = false)
	private String webStatus;
	/**倉庫狀態**/
	@Column(name = "STOREHOUSE_STATUS", nullable = false)
	private String storehouseStatus;
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
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public String getWebStatus() {
		return webStatus;
	}
	public void setWebStatus(String webStatus) {
		this.webStatus = webStatus;
	}
	public String getStorehouseStatus() {
		return storehouseStatus;
	}
	public void setStorehouseStatus(String storehouseStatus) {
		this.storehouseStatus = storehouseStatus;
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
