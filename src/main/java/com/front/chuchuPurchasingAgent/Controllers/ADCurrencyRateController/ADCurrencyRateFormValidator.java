package com.front.chuchuPurchasingAgent.Controllers.ADCurrencyRateController;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADCurrencyRateFormValidator {
	/**
	 * 表單驗證
	 * @param form
	 * @param result
	 * @throws Exception
	 */
	public void validate(ADCurrencyRateForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getCurrencyId())) {
			result.rejectValue("currencyId", "error", "請選擇");
		}
		
		if(!Optional.ofNullable(form.getRate()).isPresent()) {
			result.rejectValue("rate", "error", "請輸入");
		}
	}
}
