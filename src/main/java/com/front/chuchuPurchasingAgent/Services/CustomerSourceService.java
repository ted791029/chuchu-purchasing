package com.front.chuchuPurchasingAgent.Services;

import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerSource.ImCustomerSourceFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerSource.OtherPlaceFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerSource.PTTFactory;

@Service
public class CustomerSourceService {
	
	public String getSourceId(String code) {
		return this.getImCustomerSourceFactory(code)
				.getCustomerSource()
				.getSourceId();
		
	}
	
	private ImCustomerSourceFactory getImCustomerSourceFactory(String code) {
		if(Constants.PTT_CODE.equals(code)) return new PTTFactory();
		return new OtherPlaceFactory();
	}
}
