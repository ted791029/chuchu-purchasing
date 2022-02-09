package com.front.chuchuPurchasingAgent.Factorys.Class.LocalFeeType;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.LocalFeeType.ByProdcut;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.LocalFeeType;

public class ByProductFactory extends ImLocalFeeTypeFactory{

	@Override
	public LocalFeeType getImLocalFeeFactory() {
		return new ByProdcut();
	}

}
