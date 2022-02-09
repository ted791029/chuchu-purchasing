package com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerType.Normal;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerType;

public class NormalFactory extends ImCustomerTypeFactory{

	@Override
	public CustomerType getCustomerType() throws Exception {
		// TODO Auto-generated method stub
		return new Normal();
	}

}
