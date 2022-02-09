package com.front.chuchuPurchasingAgent.Controllers.ADPracelRouteNoteController;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Controllers.Form;

public class ADParcelRouteNoteForm implements Form{

	private String id;

	private String countryParcelRouteId;

	private String fromWhereId;

	private String content;

	private Timestamp createTime;

	private String creator;

	private Timestamp modifyTime;

	private String modifier;
	
	private String urlSuffix;
	
	private String countryId;
	
	private String routeId;

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

	public String getUrlSuffix() {
		return urlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.urlSuffix = urlSuffix;
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

}
