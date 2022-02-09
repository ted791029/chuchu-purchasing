package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.LocalFee;

public class LocalFeeBuilder {
	
	private LocalFee localFee;
	
	public LocalFeeBuilder() {
		this.localFee = new LocalFee();
	}
	public LocalFeeBuilder(LocalFee localFee) {
		this.localFee = localFee;
	}
	
	public LocalFeeBuilder buildId(String id) {
		this.localFee.setId(id);
		return this;
	}
	
	public LocalFeeBuilder buildUrl(String url) {
		this.localFee.setUrl(url);
		return this;
	}
	
	public LocalFeeBuilder buildTypeId(String typeId) {
		this.localFee.setTypeId(typeId);
		return this;
	}
	
	
	public LocalFeeBuilder buildCreateTime(Timestamp createTime) {
		this.localFee.setCreateTime(createTime);
		return this;
	}
	public LocalFeeBuilder buildCreator(String creator) {
		this.localFee.setCreator(creator);
		return this;
	}
	public LocalFeeBuilder buildModifyTime(Timestamp modifyTime) {
		this.localFee.setModifyTime(modifyTime);
		return this;
	}
	public LocalFeeBuilder buildModifier(String modifier) {
		this.localFee.setModifier(modifier);
		return this;
	}
	
	public LocalFee build() {
		return this.localFee;
	}
}
