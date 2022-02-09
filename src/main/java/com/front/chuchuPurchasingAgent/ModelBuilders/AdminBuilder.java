package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.Admin;

public class AdminBuilder {
	
	private Admin admin;
	
	public AdminBuilder() {
		this.admin = new Admin();
	}
	
	public AdminBuilder(Admin admin) {
		this.admin = admin;
	}
	
	public AdminBuilder buildId(String id) {
		this.admin.setId(id);
		return this;
	}	
	public AdminBuilder buildName(String name) {
		this.admin.setName(name);;
		return this;
	}	
	public AdminBuilder buildAccount(String account) {
		this.admin.setAccount(account);
		return this;
	}
	public AdminBuilder buildPassword(String password) {
		this.admin.setPassword(password);
		return this;
	}
	public AdminBuilder buildCreateTime(Timestamp createTime) {
		this.admin.setCreateTime(createTime);
		return this;
	}
	public AdminBuilder buildCreator(String creator) {
		this.admin.setCreator(creator);
		return this;
	}
	public AdminBuilder buildModifyTime(Timestamp modifyTime) {
		this.admin.setModifyTime(modifyTime);
		return this;
	}
	public AdminBuilder buildModifier(String modifier) {
		this.admin.setModifier(modifier);
		return this;
	}
	public Admin build() {
		return this.admin;
	}
}
