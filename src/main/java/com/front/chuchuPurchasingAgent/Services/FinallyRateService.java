package com.front.chuchuPurchasingAgent.Services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsHasOtherType;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsOldCustomer;
import com.front.chuchuPurchasingAgent.Factorys.Interface.IsVip;
import com.front.chuchuPurchasingAgent.Inputs.FinallyInputs;
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyRateBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.SettingRateBuilder;
import com.front.chuchuPurchasingAgent.Models.CurrencyRate;
import com.front.chuchuPurchasingAgent.Models.MyOrder;
import com.front.chuchuPurchasingAgent.Models.SettingRate;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.NumberUtil;

@Service
public class FinallyRateService{
	
	@Autowired
	private SettingRateService settingRateService;
	@Autowired
	private MemberService memberSerivce;
	@Autowired
	private QueryProductSerivce queryProductSerivce;
	@Autowired
	private CustomerSourceService customerSourceService;
	@Autowired
	private CustomerTypeService customerTypeService;
	@Autowired
	private CurrencyRateService currencyRateService;
	
	
	/**
	 * 取得設定匯率
	 * @return
	 * @throws Exception
	 */
	public double getRate(FinallyInputs inputs) throws Exception {
		double settingRate = this.getSettingRate(inputs);
		CurrencyRate currencyRateEx = new CurrencyRateBuilder()
				.buildCurrencyId(inputs.getCurrencyId())
				.build();
		Optional<CurrencyRate> currencyRateOptional = currencyRateService.findOne(currencyRateEx);
		if(!currencyRateOptional.isPresent()) {
			LogUtil.setInfoLog("currencyId: " + inputs.getCurrencyId() + " 查無台銀費率");
			throw new Exception();
		}
		double currencyRate = currencyRateOptional.get().getRate();
		return NumberUtil.strip(currencyRate * settingRate);
	}
	//取得settingRate
	private double getSettingRate(FinallyInputs inputs) throws Exception {
		String sourceId = customerSourceService.getSourceId(inputs.getCode());
		String typeId = this.getTypeId(inputs, sourceId);
		SettingRate settingRateEx = new SettingRateBuilder()
				.buildWhereId(sourceId)
				.buildCustomerTypeId(typeId)
				.buildCountryId(inputs.getCountryId())
				.build();
		Optional<SettingRate> settingRateOptional = settingRateService.findOne(settingRateEx);
		if(!settingRateOptional.isPresent()) {
			LogUtil.setInfoLog("countryId: " + inputs.getCountryId() + " 查無設定費率");
			throw new Exception();
		}
		return settingRateOptional.get().getRate();
	}
	
	private String getTypeId(FinallyInputs inputs, String sourceId) throws Exception {
		IsHasOtherType isHasOtherType = this.geIsHasOtherType(inputs, sourceId);
		IsOldCustomer isOldCustomer = this.getIsOldCustomer(inputs);
		IsVip isVip = this.getIsVip(inputs);		
		return customerTypeService.getTypeId(isHasOtherType, isOldCustomer, isVip);
	}
	
	private IsHasOtherType geIsHasOtherType(FinallyInputs inputs, String sourceId) {
		//實作檢查是否有其他型別方法
		return new IsHasOtherType() {			
			@Override
			//條件
			public boolean check() {
				boolean isFromOther = Constants.FROM_OTHER.equals(sourceId);
				return !isFromOther && Arrays.stream(Constants.HAVE_DISCOUNT_COUNTRY).anyMatch(inputs.getCountryId() :: equals);
			}
		};
	}
	
	private IsOldCustomer getIsOldCustomer(FinallyInputs inputs) {
		//實作檢查是否為老客戶方法
		return new IsOldCustomer() {			
			@Override
			//條件
			public boolean check() {
				//TODO 取得訂單
				Optional<MyOrder> orderOptional = Optional.of(new MyOrder());
				if(!orderOptional.isPresent()) return false;
				//TODO 檢查訂單狀態
				return false;
			}
		};
	}
	
	private IsVip getIsVip(FinallyInputs inputs) {
		//實作檢查是否為VIP
		return new IsVip() {
			@Override
			//條件
			public boolean check() {
				return queryProductSerivce.getTotalPrice(inputs.getProducts()) >= inputs.getPrice();
			}
		};
	}

}
