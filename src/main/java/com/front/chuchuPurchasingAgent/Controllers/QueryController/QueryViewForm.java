package com.front.chuchuPurchasingAgent.Controllers.QueryController;

public class QueryViewForm extends QueryForm{
	/**商品合計**/
	private int allTotalPrice;
	/**代買費用**/
	private int purchasingPrice;
	/**當地手續費**/
	private int localFeePrice;
	/**第一階段費用**/
	private int firstStagePrice;
	
	public int getAllTotalPrice() {
		return allTotalPrice;
	}

	public void setAllTotalPrice(int allTotalPrice) {
		this.allTotalPrice = allTotalPrice;
	}

	public int getPurchasingPrice() {
		return purchasingPrice;
	}

	public void setPurchasingPrice(int purchasingPrice) {
		this.purchasingPrice = purchasingPrice;
	}

	public int getLocalFeePrice() {
		return localFeePrice;
	}

	public void setLocalFeePrice(int localFeePrice) {
		this.localFeePrice = localFeePrice;
	}

	public int getFirstStagePrice() {
		return firstStagePrice;
	}

	public void setFirstStagePrice(int firstStagePrice) {
		this.firstStagePrice = firstStagePrice;
	}
	
}
