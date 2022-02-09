package com.front.chuchuPurchasingAgent.FactoryProducts.Class.CustomerSouce;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.FactoryProducts.Interface.CustomerSource;

public class PTT implements CustomerSource{

	@Override
	public String getSourceId() {
		// TODO Auto-generated method stub
		return Constants.FROM_PTT;
	}

}
