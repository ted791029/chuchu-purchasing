package com.front.chuchuPurchasingAgent.Factorys.Class.CustomerSource;

import com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerSouce.OtherPlace;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerSource;

public class OtherPlaceFactory extends ImCustomerSourceFactory{

	@Override
	public CustomerSource getCustomerSource() {
		return new OtherPlace();
	}

}
