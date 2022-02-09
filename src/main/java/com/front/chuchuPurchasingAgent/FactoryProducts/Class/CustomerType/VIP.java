package com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerType;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerType;

public class VIP implements CustomerType{

	@Override
	public String getTypeId() {
		// TODO Auto-generated method stub
		return Constants.CUSTOMER_TYPE_VIP;
	}

}
