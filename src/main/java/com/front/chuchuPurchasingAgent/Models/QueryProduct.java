package com.front.chuchuPurchasingAgent.Models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PA_QUERY_PRODUCT")
public class QueryProduct {
	/**唯一碼**/
	@Id
	private String id;
	/**詢問單ID**/
	@Column(name = "QUERY_ID")
	private String queryId;
	/**商品名稱**/
	private String name;
	/**商品網址**/
	private String url;
	/**商品規格**/
	private String format;
	/**商品價格**/
	private Integer price;
	/**商品數量**/
	private Integer count;
	/**備註**/
	private String note;
	/**匯率**/
	private Double rate;
	/**小計**/
	private Integer total;
	/**創造日期**/
	@Column(name = "CREATE_TIME", nullable = false)
	private Timestamp createTime;
	/**創造者**/
	private String creator;
	/**異動日期**/
	@Column(name = "MODIFY_TIME", nullable = false)
	private Timestamp modifyTime;
	/**異動者**/
	private String modifier;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setCount(Integer count) {
		this.count = count;
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
