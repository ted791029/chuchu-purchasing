package com.front.chuchuPurchasingAgent.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.Factorys.Class.LocalFeeType.ByProductFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.LocalFeeType.ByWebFactory;
import com.front.chuchuPurchasingAgent.Factorys.Class.LocalFeeType.ImLocalFeeTypeFactory;
import com.front.chuchuPurchasingAgent.Models.LocalFee;
import com.front.chuchuPurchasingAgent.Models.QueryProduct;
import com.front.chuchuPurchasingAgent.Repositories.LocalFeeRepository;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;

@Service
public class LocalFeeService {
	@Autowired
	private LocalFeeRepository localFeeRepository;
	
	public List<LocalFee> findAll(LocalFee localFee){
		Example<LocalFee> localFeeEx = Example.of(localFee);
		return localFeeRepository.findAll(localFeeEx);
	}
	/**
	 * 取得手續費
	 * @param products
	 * @param localFees
	 * @return
	 * @throws Exception
	 */
	public int getLocalFeePrice(List<QueryProduct> products, List<LocalFee> localFees) throws Exception {
		int localFeePrice = 0;
		Map<String, Integer> map = this.getSearchMap(products, localFees);
		for(LocalFee localFee: localFees) {
			ImLocalFeeTypeFactory imLocalFeeTypeFactory = this.getImLocalFeeTypeFactory(localFee.getTypeId());
			Optional<ImLocalFeeTypeFactory> factoryOptional = Optional.of(imLocalFeeTypeFactory);
			if(!factoryOptional.isPresent()) {
				LogUtil.setInfoLog("取得手續費型別有誤");
				throw new Exception();
			}
			String key = localFee.getUrl();
			if(!map.containsKey(key)) continue;
			int count = map.get(key);
			localFeePrice += factoryOptional.get()
					.getImLocalFeeFactory()
					.calculate(count);
		}
		return localFeePrice;
	}
	
	/**
	 * 取得要收取手續費的商品url及數量map
	 * @param products
	 * @param localFees
	 * @return
	 */
	private Map<String, Integer> getSearchMap(List<QueryProduct> products, List<LocalFee> localFees){
		String key = "";
		int count = 0;
		Map<String, Integer> map =  new HashMap<>();
		for(QueryProduct product : products) {
			for(LocalFee localFee : localFees) {
				if(product.getUrl().contains(localFee.getUrl())) {
					key = localFee.getUrl();
					if(map.containsKey(key)) {
						count = map.get(key);
						map.put(key, ++count);
						continue;
					}
					map.put(key, 1);
				}
			}
		}
		return map;
	}
	
	/**
	 * 取得產生工廠
	 * @param typeId
	 * @return
	 */
	private ImLocalFeeTypeFactory getImLocalFeeTypeFactory(String typeId) {
		switch (typeId) {
		case Constants.LOCAL_FEE_TYPE_BYPRODUCT:
			return new ByProductFactory();
		case Constants.LOCAL_FEE_TYPE_BYWEB:
			return new ByWebFactory();
		default:
			return null;
		}
	}
}
