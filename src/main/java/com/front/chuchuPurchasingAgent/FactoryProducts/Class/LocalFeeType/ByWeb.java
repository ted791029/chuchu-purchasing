package com.front.chuchuPurchasingAgent.FactoryProducts.Class.LocalFeeType;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.LocalFeeType;

public class ByWeb implements LocalFeeType{

	@Override
	public int calculate(int count) {
		count = 1;
		return Constants.LOCAL_FEE_TYPE_BYWEB_STANDARD * count;
	}
}
