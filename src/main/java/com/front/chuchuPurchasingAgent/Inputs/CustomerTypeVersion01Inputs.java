package com.front.chuchuPurchasingAgent.Inputs;

import com.front.chuchuPurchasingAgent.Factorys.Interface.IsHasOtherType;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsOldCustomer;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsVip;

public class CustomerTypeVersion01Inputs {
	
	private IsHasOtherType isHasOtherType;
	
	private IsOldCustomer isOldCustomer;
	
	private IsVip isVip;

	public IsHasOtherType getIsHasOtherType() {
		return isHasOtherType;
	}

	public void setIsHasOtherType(IsHasOtherType isHasOtherType) {
		this.isHasOtherType = isHasOtherType;
	}

	public IsOldCustomer getIsOldCustomer() {
		return isOldCustomer;
	}

	public void setIsOldCustomer(IsOldCustomer isOldCustomer) {
		this.isOldCustomer = isOldCustomer;
	}

	public IsVip getIsVip() {
		return isVip;
	}

	public void setIsVip(IsVip isVip) {
		this.isVip = isVip;
	}
	
}
