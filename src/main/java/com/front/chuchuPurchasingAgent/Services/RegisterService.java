package com.front.chuchuPurchasingAgent.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Controllers.RegisterController.RegisterForm;
import com.front.chuchuPurchasingAgent.Models.Member;

@Service
public class RegisterService {
	
	@Autowired
	private MemberService memberSevice;
	
	public void doRegister(Member member) throws Exception {
		memberSevice.registerSetting(member);
		memberSevice.save(member);
	}
	/**
	 * form 轉 model
	 * @throws Exception 
	 */
	public Member formToMember(RegisterForm form) throws Exception {
		return memberSevice.formToModel(form);
	}
}
