package com.front.chuchuPurchasingAgent.Controllers.ADMemebrController;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.front.chuchuPurchasingAgent.Controllers.Form;

public class ADMemberForm implements Form{
	
	private String account;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
