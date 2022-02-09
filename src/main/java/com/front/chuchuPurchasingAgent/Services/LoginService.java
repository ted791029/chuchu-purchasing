package com.front.chuchuPurchasingAgent.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Controllers.LoginController.LoginForm;
import com.front.chuchuPurchasingAgent.Models.Member;

@Service
public class LoginService {
	
	@Autowired
	private MemberService memberService;
	
	public Optional<Member> login(LoginForm form) throws Exception {
		Member memberEx = memberService.formToModel(form);
		return memberService.findOne(memberEx);
	}
}
