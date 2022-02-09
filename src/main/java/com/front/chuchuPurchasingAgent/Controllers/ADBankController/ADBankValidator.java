package com.front.chuchuPurchasingAgent.Controllers.ADBankController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADBankValidator {
	/**
	 * 檢驗方法
	 * @param form
	 * @param result
	 */
	public void validate(ADBankForm form, BindingResult result) {
		if(StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getAccount())) {
			result.rejectValue("account", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getStatus())) {
			result.rejectValue("status", "error", "請選擇");
		}
	}
}
