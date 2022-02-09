package com.front.chuchuPurchasingAgent.Controllers.ADSettingRateController;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADSettingRateValidator {
	/**
	 * 檢驗方法
	 * @param form
	 * @param result
	 */
	public void validate(ADSettingRateForm form, BindingResult result) {
		if(StringUtils.isBlank(form.getWhereId())) {
			result.rejectValue("whereId", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getCountryId())) {
			result.rejectValue("countryId", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getCustomerTypeId())) {
			result.rejectValue("customerTypeId", "error", "請選擇");
		}
		if(!Optional.ofNullable(form.getRate()).isPresent()) {
			result.rejectValue("rate", "error", "請輸入");
		}
	}
}
