package com.front.chuchuPurchasingAgent.Controllers.LoginController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class LoginFormValidator {
	/**
	 * 表單驗證
	 * @param form
	 * @param result
	 * @throws Exception
	 */
	public void validate(LoginForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getAccount())) {
			result.rejectValue("account", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getPassword())) {
			result.rejectValue("password", "error", "請填寫");
		}
		//密碼長度小於六
		if(StringUtils.isNotBlank(form.getPassword()) && form.getPassword().length() < 6) {
			result.rejectValue("password", "error", "長度小於6碼");
		}

	}
}
