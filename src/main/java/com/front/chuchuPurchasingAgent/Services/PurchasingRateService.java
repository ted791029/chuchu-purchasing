package com.front.chuchuPurchasingAgent.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Models.PurchasingRate;
import com.front.chuchuPurchasingAgent.Repositories.PurchasingRateRepository;

@Service
public class PurchasingRateService {
	
	@Autowired
	private PurchasingRateRepository purchasingRateRepository;
	
	public Optional<PurchasingRate> findOne(PurchasingRate purchasingRate){
		Example<PurchasingRate> purchasingRateEx = Example.of(purchasingRate);
		return purchasingRateRepository.findOne(purchasingRateEx);
	}
	/**
	 * 取得代買費用
	 * @param allTotalPrice
	 * @param purchasingRateOptional
	 * @return
	 */
	public int getPurchasingPrice(int allTotalPrice, Optional<PurchasingRate> purchasingRateOptional) {
		return (int) Math.ceil(allTotalPrice * purchasingRateOptional.get().getRate());
	}
	
}
