package com.front.chuchuPurchasingAgent.Factorys.Class.LocalFeeType;

import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.LocalFeeType;
import com.front.chuchuPurchasingAgent.Factorys.Interface.LocalFeeTypeFactory;

public abstract class ImLocalFeeTypeFactory implements LocalFeeTypeFactory{

	@Override
	public abstract LocalFeeType getImLocalFeeFactory();

}
