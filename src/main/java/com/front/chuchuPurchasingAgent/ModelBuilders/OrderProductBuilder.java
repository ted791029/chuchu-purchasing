package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.OrderProduct;

public class OrderProductBuilder {
	
private OrderProduct product;
	
	public OrderProductBuilder() {
		this.product = new OrderProduct();
	}
	public OrderProductBuilder(OrderProduct product) {
		this.product = product;
	}
	
	public OrderProductBuilder buildId(String id) {
		this.product.setId(id);
		return this;
	}
	
	public OrderProductBuilder buildOrderId(String orderId) {
		this.product.setOrderId(orderId);
		return this;
	}
	
	public OrderProductBuilder buildName( String name) {
		this.product.setName(name);
		return this;
	}
	
	public OrderProductBuilder buildUrl(String url) {
		this.product.setUrl(url);
		return this;
	}
	
	public OrderProductBuilder buildFormat(String format) {
		this.product.setFormat(format);
		return this;
	}
	
	public OrderProductBuilder buildPrice(int price) {
		this.product.setPrice(price);
		return this;
	}
	
	public OrderProductBuilder buildCount(int count) {
		this.product.setCount(count);
		return this;
	}
	
	public OrderProductBuilder buildRate(double rate) {
		this.product.setRate(rate);;
		return this;
	}
	
	public OrderProductBuilder buildTotal(int total) {
		this.product.setTotal(total);;
		return this;
	}
	
	public OrderProductBuilder buildCreateTime(Timestamp createTime) {
		this.product.setCreateTime(createTime);
		return this;
	}
	
	public OrderProductBuilder buildCreator(String creator) {
		this.product.setCreator(creator);
		return this;
	}
	
	public OrderProductBuilder buildModifyTime(Timestamp modifyTime) {
		this.product.setModifyTime(modifyTime);
		return this;
	}
	
	public OrderProductBuilder buildModifier(String modifier) {
		this.product.setModifier(modifier);
		return this;
	}
	
	public OrderProduct build() {
		return this.product;
	}
}
