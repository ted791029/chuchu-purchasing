package com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerType.VIP;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerType;

public class VIPFactory extends ImCustomerTypeFactory{

	@Override
	public CustomerType getCustomerType() throws Exception {
		return new VIP();
	}

}
