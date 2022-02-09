package com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerType.Old;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerType;

public class OldFactory extends ImCustomerTypeFactory{

	@Override
	public CustomerType getCustomerType() throws Exception {
		return new Old();
	}

}
