package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.Bank;

public class BankBuilder {
	
	private Bank bank;
	
	public BankBuilder() {
		this.bank = new Bank();
	}
	
	public BankBuilder(Bank bank) {
		this.bank = bank;
	}
	
	public BankBuilder buildId(String id) {
		this.bank.setId(id);
		return this;
	}
	public BankBuilder buildName(String name) {
		this.bank.setName(name);
		return this;
	}
	public BankBuilder buildAccount(String account) {
		this.bank.setAccount(account);
		return this;
	}
	public BankBuilder buildStatus(String status) {
		this.bank.setStatus(status);
		return this;
	}
	public BankBuilder buildCreateTime(Timestamp createTime) {
		this.bank.setCreateTime(createTime);
		return this;
	}
	public BankBuilder buildCreator(String creator) {
		this.bank.setCreator(creator);
		return this;
	}
	public BankBuilder buildModifyTime(Timestamp modifyTime) {
		this.bank.setModifyTime(modifyTime);
		return this;
	}
	public BankBuilder buildModifier(String modifier) {
		this.bank.setModifier(modifier);
		return this;
	}
	public Bank build() {
		return this.bank;
	}
}
