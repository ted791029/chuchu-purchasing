package com.front.chuchuPurchasingAgent.Controllers.ADLoginController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADLoginFormValidator {
	/**
	 * 表單驗證
	 * @param form
	 * @param result
	 * @throws Exception
	 */
	public void validate(ADLoginForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getAccount())) {
			result.rejectValue("account", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getPassword())) {
			result.rejectValue("password", "error", "請填寫");
		}

	}
}
