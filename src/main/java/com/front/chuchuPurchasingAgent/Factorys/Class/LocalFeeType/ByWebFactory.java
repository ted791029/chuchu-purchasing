package com.front.chuchuPurchasingAgent.Factorys.Class.LocalFeeType;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.LocalFeeType.ByWeb;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.LocalFeeType;

public class ByWebFactory extends ImLocalFeeTypeFactory{

	@Override
	public LocalFeeType getImLocalFeeFactory() {
		return new ByWeb();
	}

}
