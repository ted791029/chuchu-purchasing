package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.CustomerType;

public class CustomerTypeBuilder {
	
	private CustomerType customerType;
	
	public CustomerTypeBuilder() {
		this.customerType = new CustomerType();
	}
	
	public CustomerTypeBuilder(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	public CustomerTypeBuilder buildId(String id) {
		this.customerType.setId(id);;
		return this;
	}
	public CustomerTypeBuilder buildName(String name) {
		this.customerType.setName(name);;
		return this;
	}
	public CustomerTypeBuilder buildStatus(String status) {
		this.customerType.setStatus(status);;
		return this;
	}
	public CustomerTypeBuilder buildCreateTime(Timestamp createTime) {
		this.customerType.setCreateTime(createTime);
		return this;
	}
	public CustomerTypeBuilder buildCreator(String creator) {
		this.customerType.setCreator(creator);
		return this;
	}
	public CustomerTypeBuilder buildModifyTime(Timestamp modifyTime) {
		this.customerType.setModifyTime(modifyTime);
		return this;
	}
	public CustomerTypeBuilder buildModifier(String modifier) {
		this.customerType.setModifier(modifier);
		return this;
	}
	public CustomerType build() {
		return this.customerType;
	}
}
