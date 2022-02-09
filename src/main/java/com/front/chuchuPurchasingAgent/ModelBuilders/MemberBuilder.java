package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.Member;

public class MemberBuilder{
	
	private Member member;
	
	public MemberBuilder() {
		 this.member = new Member();
	}
	
	public MemberBuilder(Member member) {
		this.member = member;
	}
	 
	public MemberBuilder buildId(String id) {
		this.member.setId(id);
		return this;
	}
	public MemberBuilder buildAccount(String account) {
		this.member.setAccount(account);
		return this;
	}
	public MemberBuilder buildPassword(String password) {
		this.member.setPassword(password);
		return this;
	}
	public MemberBuilder buildEmail(String email) {
		this.member.setEmail(email);
		return this;
	}
	public MemberBuilder buildName(String name) {
		this.member.setName(name);
		return this;
	}
	public MemberBuilder buildPhone(String phone) {
		this.member.setPhone(phone);
		return this;
	}
	public MemberBuilder buildAddress(String address) {
		this.member.setAddress(address);
		return this;
	}
	public MemberBuilder buildIdntityCard(String idntityCard) {
		this.member.setIdntityCard(idntityCard);
		return this;
	}
	public MemberBuilder buildCreateTime(Timestamp createTime) {
		this.member.setCreateTime(createTime);
		return this;
	}
	public MemberBuilder buildCreator(String creator) {
		this.member.setCreator(creator);
		return this;
	}
	public MemberBuilder buildModifyTime(Timestamp modifyTime) {
		this.member.setModifyTime(modifyTime);
		return this;
	}
	public MemberBuilder buildModifier(String modifier) {
		this.member.setModifier(modifier);
		return this;
	}
	public Member build() {
		return this.member;
	}

}
