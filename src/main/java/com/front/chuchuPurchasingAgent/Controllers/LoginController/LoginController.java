package com.front.chuchuPurchasingAgent.Controllers.LoginController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.MessageConstants;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.Services.LoginService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/front/login")
public class LoginController {
	private String prefix = "login/";
	
	@Autowired
	private LoginFormValidator validator;
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/index")
	public String index(HttpServletRequest request, LoginForm form, Model model) {
		try {
			//已經登入
			Member member = SessionUtil.getMember(request);
			if(Optional.ofNullable(member).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_IS_LONGINING);
				model.addAttribute("relativeUrl", Constants.INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			model.addAttribute("loginForm", form);
			return  prefix + "index";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@PostMapping("/doLogin")
	public String doLogin(HttpServletRequest request, LoginForm form, BindingResult result, Model model){
		try {
			//已經登入
			Member member = SessionUtil.getMember(request);
			Optional<Member> memberOptional = Optional.ofNullable(member);
			if(memberOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_IS_LONGINING);
				model.addAttribute("relativeUrl", Constants.INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				return prefix + "index";
			}
			memberOptional = loginService.login(form);
			//查無資料
			if(!memberOptional.isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_MEMBER_ACCOUNT_PASSWORD_ERROR);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			SessionUtil.setMember(request, memberOptional.get());
			model.addAttribute("message", MessageConstants.MESSAGE_LOGIN_SUCCESS);
			model.addAttribute("relativeUrl", Constants.INTRO_INDEX_URI);
			return "common/jsAlert";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpServletRequest request, LoginForm form, Model model) {
		try {
			SessionUtil.removeMember(request);
			model.addAttribute("message", MessageConstants.MESSAGE_SIGN_OUT);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
}
