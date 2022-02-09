package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.ParcelRouteNote;

public class ParcelRouteNoteBuilder {
	
	private ParcelRouteNote parcelRouteNote;
	
	public ParcelRouteNoteBuilder() {
		this.parcelRouteNote = new ParcelRouteNote();
	}
	public ParcelRouteNoteBuilder(ParcelRouteNote parcelRouteNote) {
		this.parcelRouteNote = parcelRouteNote;
	}
	
	public ParcelRouteNoteBuilder buildId(String id){
		this.parcelRouteNote.setId(id);
		return this;
	}
	public ParcelRouteNoteBuilder buildCountryParcelRouteId(String countryParcelRouteId){
		this.parcelRouteNote.setCountryParcelRouteId(countryParcelRouteId);;
		return this;
	}
	public ParcelRouteNoteBuilder buildFromWhereId(String fromWhereId){
		this.parcelRouteNote.setFromWhereId(fromWhereId);;
		return this;
	}
	public ParcelRouteNoteBuilder buildContent(String content){
		this.parcelRouteNote.setContent(content);;
		return this;
	}
	public ParcelRouteNoteBuilder buildCreateTime(Timestamp createTime) {
		this.parcelRouteNote.setCreateTime(createTime);
		return this;
	}
	public ParcelRouteNoteBuilder buildCreator(String creator) {
		this.parcelRouteNote.setCreator(creator);
		return this;
	}
	public ParcelRouteNoteBuilder buildModifyTime(Timestamp modifyTime) {
		this.parcelRouteNote.setModifyTime(modifyTime);
		return this;
	}
	public ParcelRouteNoteBuilder buildModifier(String modifier) {
		this.parcelRouteNote.setModifier(modifier);
		return this;
	}
	public ParcelRouteNote build() {
		return this.parcelRouteNote;
	}
}
