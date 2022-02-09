package com.front.chuchuPurchasingAgent.Controllers.ADCurrencyController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADCurrencyFormValidator {
	/**
	 * 表單驗證
	 * @param form
	 * @param result
	 * @throws Exception
	 */
	public void validate(ADCurrencyForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getAreaId())) {
			result.rejectValue("areaId", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}
	}
}
