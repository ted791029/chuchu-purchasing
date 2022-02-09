package com.front.chuchuPurchasingAgent.ModelBuilders;

import java.sql.Timestamp;

import com.front.chuchuPurchasingAgent.Models.QueryProduct;

public class QeryProductBuilder {
	
	private QueryProduct product;
	
	public QeryProductBuilder() {
		this.product = new QueryProduct();
	}
	public QeryProductBuilder(QueryProduct product) {
		this.product = product;
	}
	
	public QeryProductBuilder buildId(String id) {
		this.product.setId(id);
		return this;
	}
	
	public QeryProductBuilder buildQueryId(String queryId) {
		this.product.setQueryId(queryId);
		return this;
	}
	
	public QeryProductBuilder buildName( String name) {
		this.product.setName(name);
		return this;
	}
	
	public QeryProductBuilder buildUrl(String url) {
		this.product.setUrl(url);
		return this;
	}
	
	public QeryProductBuilder buildFormat(String format) {
		this.product.setFormat(format);
		return this;
	}
	
	public QeryProductBuilder buildPrice(int price) {
		this.product.setPrice(price);
		return this;
	}
	
	public QeryProductBuilder buildCount(int count) {
		this.product.setCount(count);
		return this;
	}
	
	public QeryProductBuilder buildRate(double rate) {
		this.product.setRate(rate);;
		return this;
	}
	
	public QeryProductBuilder buildTotal(int total) {
		this.product.setTotal(total);;
		return this;
	}
	
	public QeryProductBuilder buildCreateTime(Timestamp createTime) {
		this.product.setCreateTime(createTime);
		return this;
	}
	
	public QeryProductBuilder buildCreator(String creator) {
		this.product.setCreator(creator);
		return this;
	}
	
	public QeryProductBuilder buildModifyTime(Timestamp modifyTime) {
		this.product.setModifyTime(modifyTime);
		return this;
	}
	
	public QeryProductBuilder buildModifier(String modifier) {
		this.product.setModifier(modifier);
		return this;
	}
	
	public QueryProduct build() {
		return this.product;
	}
}
