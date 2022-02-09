package com.front.chuchuPurchasingAgent.Factorys.Class.CustomerSource;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerSouce.PTT;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerSource;

public class PTTFactory extends ImCustomerSourceFactory{

	@Override
	public CustomerSource getCustomerSource() {
		return new PTT();
	}

}
