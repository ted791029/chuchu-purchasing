package com.front.chuchuPurchasingAgent.Controllers.MemberController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.front.chuchuPurchasingAgent.tool.util.ValidatorUtil;

@Component
public class MemberFormValidator {
	/**
	 * 表單驗證
	 * @param form
	 * @param result
	 * @throws Exception
	 */
	public void validate(MemberForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getName())) {
			result.rejectValue("name", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getEmail())) {
			result.rejectValue("email", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getEmail())) {
			result.rejectValue("email", "error", "請填寫");
		}else {
			if(!ValidatorUtil.isValidEmail(form.getEmail())) {
				result.rejectValue("email", "error", "格式有誤");
			}
		}
		if(StringUtils.isBlank(form.getPhone())) {
			result.rejectValue("phone", "error", "請填寫");
		}else {
			if(!ValidatorUtil.isValidMobile(form.getPhone())) {
				result.rejectValue("phone", "error", "格式有誤");
			}
		}
		if(StringUtils.isBlank(form.getIdntityCard())) {
			result.rejectValue("idntityCard", "error", "請填寫");
		}else {
			if(!ValidatorUtil.isValidTWPID(form.getIdntityCard())) {
				result.rejectValue("idntityCard", "error", "格式有誤");
			}
		}
		
	}
}
