package com.front.chuchuPurchasingAgent.Services;

import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType.ImCustomerTypeFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType.NormalFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType.OldFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.CustomerType.VIPFactory;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsHasOtherType;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsOldCustomer;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsVip;

@Service
public class CustomerTypeService {
	
	public String getTypeId(IsHasOtherType isHasOtherType, IsOldCustomer isOldCustomer, IsVip isVip) throws Exception {
		return this.geImCustomerTypeFactory(isHasOtherType, isOldCustomer, isVip)
		.getCustomerType()
		.getTypeId();
	}
	
	private ImCustomerTypeFactory geImCustomerTypeFactory(IsHasOtherType isHasOtherType, IsOldCustomer isOldCustomer, IsVip isVip) {
		if(!isHasOtherType.check()) return new NormalFactory();
		if(isOldCustomer.check()) return new OldFactory();
		if(isVip.check()) return new VIPFactory();
		return new NormalFactory();
	}
}
