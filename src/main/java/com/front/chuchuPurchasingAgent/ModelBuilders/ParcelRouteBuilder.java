package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.ParcelRoute;

public class ParcelRouteBuilder {
	private ParcelRoute parcelRoute;
	
	public ParcelRouteBuilder() {
		this.parcelRoute = new ParcelRoute();
	}
	
	public ParcelRouteBuilder(ParcelRoute parcelRoute) {
		this.parcelRoute = parcelRoute;
	}
	
	public ParcelRouteBuilder buildId(String id) {
		this.parcelRoute.setId(id);
		return this;
	}
	public ParcelRouteBuilder buildName(String name) {
		this.parcelRoute.setName(name);
		return this;
	}
	public ParcelRouteBuilder buildStatus(String status) {
		this.parcelRoute.setStatus(status);
		return this;
	}
	public ParcelRouteBuilder buildCreateTime(Timestamp createTime) {
		this.parcelRoute.setCreateTime(createTime);
		return this;
	}
	public ParcelRouteBuilder buildCreator(String creator) {
		this.parcelRoute.setCreator(creator);
		return this;
	}
	public ParcelRouteBuilder buildModifyTime(Timestamp modifyTime) {
		this.parcelRoute.setModifyTime(modifyTime);
		return this;
	}
	public ParcelRouteBuilder buildModifier(String modifier) {
		this.parcelRoute.setModifier(modifier);
		return this;
	}
	public ParcelRoute build() {
		return this.parcelRoute;
	}
}
