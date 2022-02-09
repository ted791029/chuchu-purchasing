package com.front.chuchuPurchasingAgent.Controllers.ADPracelRouteNoteController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ADParcelRouteNoteValidator {
	
	/**
	 * 檢驗方法
	 * @param form
	 * @param result
	 */
	public void validate(ADParcelRouteNoteForm form, BindingResult result) {
		if(StringUtils.isBlank(form.getFromWhereId())) {
			result.rejectValue("fromWhereId", "error", "請選擇");
		}
		if(StringUtils.isBlank(form.getContent())) {
			result.rejectValue("content", "error", "請輸入");
		}
	}

}
