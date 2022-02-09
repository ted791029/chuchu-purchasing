package com.front.chuchuPurchasingAgent.Controllers.RegisterController;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.front.chuchuPurchasingAgent.ModelBuilders.MemberBuilder;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.Services.MemberService;

@Component
public class RegisterFormValidator {
	@Autowired
	private MemberService memberService;
	/**
	 * 表單驗證
	 * 
	 * @param form
	 * @param result
	 * @param managerService
	 * @throws Exception
	 */
	public void validate(RegisterForm form, BindingResult result) throws Exception {
		if(StringUtils.isBlank(form.getAccount())) {
			result.rejectValue("account", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getPassword())) {
			result.rejectValue("password", "error", "請填寫");
		}
		if(StringUtils.isBlank(form.getCheckPassword())) {
			result.rejectValue("checkPassword", "error", "請填寫");
		}
		if(StringUtils.isNotBlank(form.getAccount())) {
			Member memberEx = new MemberBuilder()
					.buildAccount(form.getAccount())
					.build();
			Optional<Member> memberOptional = memberService.findOne(memberEx);
			if(memberOptional.isPresent()) {				
				result.rejectValue("account", "error", "此email已被註冊");
			}
		}
		//密碼長度小於六
		if(StringUtils.isNotBlank(form.getPassword()) && form.getPassword().length() < 6) {
			result.rejectValue("password", "error", "長度小於6碼");
		}
		if(StringUtils.isNotBlank(form.getPassword()) && StringUtils.isNoneBlank(form.getCheckPassword())) {
			if(!(form.getPassword().equals(form.getCheckPassword()))) {
				result.rejectValue("checkPassword", "error", "密碼不一致");
			}
		}
	}
}
