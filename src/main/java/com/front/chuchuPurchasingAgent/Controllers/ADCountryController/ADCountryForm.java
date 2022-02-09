package com.front.chuchuPurchasingAgent.Controllers.ADCountryController;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Controllers.Form;

public class ADCountryForm implements Form{

	private String id;

	private String areaId;

	private String name;

	private String webStatus;

	private String storehouseStatus;

	private Timestamp createTime;

	private String creator;

	private Timestamp modifyTime;
	
	private String modifier;
	
	private String urlSuffix;

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

	public String getUrlSuffix() {
		return urlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.urlSuffix = urlSuffix;
	}
}
