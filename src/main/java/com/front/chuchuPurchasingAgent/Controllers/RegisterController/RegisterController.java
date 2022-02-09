package com.front.chuchuPurchasingAgent.Controllers.RegisterController;

import java.util.Optional;

import javax.management.AttributeNotFoundException;
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
import com.front.chuchuPurchasingAgent.Services.RegisterService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/front/register")
public class RegisterController {
	private String prefix = "register/";
	
	@Autowired
	private RegisterService registerService;
	@Autowired
	private RegisterFormValidator validator;
	
	/**
	 * 註冊頁
	 * @param form
	 * @param model
	 * @return
	 * @throws AttributeNotFoundException
	 */
	@GetMapping("/index")
	public String index(HttpServletRequest request, RegisterForm form, Model model) throws AttributeNotFoundException {
		try {
			//已經登入
			Member member = SessionUtil.getMember(request);
			Optional<Member> memberOptional = Optional.ofNullable(member);
			if(memberOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_IS_LONGINING);
				model.addAttribute("relativeUrl", Constants.INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			model.addAttribute("registerForm", form);
			return  prefix + "index";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	/**
	 * 註冊
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 * @throws AttributeNotFoundException
	 */
	@PostMapping("/doRegister")
	public String doRegister(HttpServletRequest request, RegisterForm form, BindingResult result, Model model) throws AttributeNotFoundException {
		try {			
			validator.validate(form, result);
			if (result.hasErrors()) {
				return prefix + "index";
			}
			Member member = registerService.formToMember(form);
			registerService.doRegister(member);
			SessionUtil.setMember(request, member);
			model.addAttribute("message", MessageConstants.MESSAGE_REGISTER_SUCCESS);
			model.addAttribute("relativeUrl", Constants.INTRO_INDEX_URI);
			return "common/jsAlert";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}

}
