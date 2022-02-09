package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_MEMBER")
public class Member {
	/**唯一碼**/
	@Id
	private String id;
	/**帳號**/
	private String account;
	/**密碼**/
	private String password;
	/**信箱**/
	private String email;
	/**姓名**/
	private String name;
	/**手機**/
	private String phone;
	/**地址**/
	private String address;
	/**身分證**/
	@Column(name = "IDNTITY_CARD")
	private String idntityCard;
	@Column(name = "STORE_NO")
	private String storeNO; 
	/**創造日期**/
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	/**創造者**/
	private String creator;
	/**異動日期**/
	@Column(name = "MODIFY_TIME")
	private Timestamp modifyTime;
	/**異動者**/
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
