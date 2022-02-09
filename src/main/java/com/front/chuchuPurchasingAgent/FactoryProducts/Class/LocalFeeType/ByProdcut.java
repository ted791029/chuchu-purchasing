package com.front.chuchuPurchasingAgent.FactoryProducts.Class.LocalFeeType;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.LocalFeeType;

public class ByProdcut implements LocalFeeType{
	
	@Override
	public int calculate(int count) {
		return Constants.LOCAL_FEE_TYPE_BYPRODUCT_STANDARD * count;
	}
}
