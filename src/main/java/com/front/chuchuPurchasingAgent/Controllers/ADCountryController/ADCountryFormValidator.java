package com.front.chuchuPurchasingAgent.Controllers.ADCountryController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADCountryFormValidator {
	/**
	 * 表單驗證
	 * @param form
	 * @param result
	 * @throws Exception
	 */
	public void validate(ADCountryForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getAreaId())) {
			result.rejectValue("areaId", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getWebStatus())) {
			result.rejectValue("webStatus", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getStorehouseStatus())) {
			result.rejectValue("storehouseStatus", "error", "請選擇");
		}
	}
}
