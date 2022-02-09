package com.front.chuchuPurchasingAgent.Controllers.OrderController;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderFindForm extends OrderForm{
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	
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
