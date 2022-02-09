package com.front.chuchuPurchasingAgent.Controllers.MemberController;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Controllers.Form;

public class MemberForm implements Form{
	
	private String id;

	private String account;

	private String password;

	private String email;

	private String name;

	private String phone;

	private String address;

	private String idntityCard;

	private String storeNO; 

	private Timestamp createTime;

	private String creator;

	private Timestamp modifyTime;

	private String modifier;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdntityCard() {
		return idntityCard;
	}

	public void setIdntityCard(String idntityCard) {
		this.idntityCard = idntityCard;
	}

	public String getStoreNO() {
		return storeNO;
	}

	public void setStoreNO(String storeNO) {
		this.storeNO = storeNO;
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
}
