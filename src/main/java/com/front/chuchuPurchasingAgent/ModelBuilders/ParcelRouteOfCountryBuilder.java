package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.ParcelRouteOfCountry;

public class ParcelRouteOfCountryBuilder {
	
	private ParcelRouteOfCountry parcelRouteOfCountry;
	
	public ParcelRouteOfCountryBuilder() {
		this.parcelRouteOfCountry = new ParcelRouteOfCountry();
	}
	
	public ParcelRouteOfCountryBuilder(ParcelRouteOfCountry parcleRouteOfCounty) {
		this.parcelRouteOfCountry = parcleRouteOfCounty;
	}
	
	public ParcelRouteOfCountryBuilder buildId(String id) {
		this.parcelRouteOfCountry.setId(id);
		return this;
	}
	public ParcelRouteOfCountryBuilder buildCountryId(String countryId) {
		this.parcelRouteOfCountry.setCountryId(countryId);
		return this;
	}
	public ParcelRouteOfCountryBuilder buildRouteId(String routeId) {
		this.parcelRouteOfCountry.setRouteId(routeId);
		return this;
	}
	public ParcelRouteOfCountryBuilder buildCreateTime(Timestamp createTime) {
		this.parcelRouteOfCountry.setCreateTime(createTime);
		return this;
	}
	public ParcelRouteOfCountryBuilder buildCreator(String creator) {
		this.parcelRouteOfCountry.setCreator(creator);
		return this;
	}
	public ParcelRouteOfCountryBuilder buildModifyTime(Timestamp modifyTime) {
		this.parcelRouteOfCountry.setModifyTime(modifyTime);
		return this;
	}
	public ParcelRouteOfCountryBuilder buildModifier(String modifier) {
		this.parcelRouteOfCountry.setModifier(modifier);
		return this;
	}
	public ParcelRouteOfCountry build() {
		return this.parcelRouteOfCountry;
	}
	
	
}
