package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.FromWhere;

public class FromWhereBuilder {
	
	private FromWhere fromWhere;
	
	public FromWhereBuilder() {
		this.fromWhere = new FromWhere();
	}
	
	public FromWhereBuilder(FromWhere fromWhere) {
		this.fromWhere = fromWhere;
	}
	public FromWhereBuilder buildId(String id) {
		this.fromWhere.setId(id);
		return this;
	}	
	public FromWhereBuilder buildName(String name) {
		this.fromWhere.setName(name);
		return this;
	}	
	public FromWhereBuilder buildStatus(String status) {
		this.fromWhere.setStatus(status);
		return this;
	}
	public FromWhereBuilder buildCreateTime(Timestamp createTime) {
		this.fromWhere.setCreateTime(createTime);
		return this;
	}
	public FromWhereBuilder buildCreator(String creator) {
		this.fromWhere.setCreator(creator);
		return this;
	}
	public FromWhereBuilder buildModifyTime(Timestamp modifyTime) {
		this.fromWhere.setModifyTime(modifyTime);
		return this;
	}
	public FromWhereBuilder buildModifier(String modifier) {
		this.fromWhere.setModifier(modifier);
		return this;
	}
	public FromWhere build() {
		return this.fromWhere;
	}

}
