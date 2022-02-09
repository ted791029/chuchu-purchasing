package com.front.chuchuPurchasingAgent.Controllers.ADParcelRouteOfCountryController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADParcelRouteOfCountryFormValidator {
	/**
	 * 檢驗方法
	 * @param form
	 * @param result
	 */
	public void validate(ADParcelRouteOfCountryForm form, BindingResult result) {
		if(StringUtils.isBlank(form.getCountryId())) {
			result.rejectValue("countryId", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getRouteId())) {
			result.rejectValue("routeId", "error", "請選擇");
		}
	}
}
