package com.front.chuchuPurchasingAgent.Controllers.ADLoginController;

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
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Services.ADLoginService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/chuAdmin/login")
public class ADLoginController {
	
	private String prefix = "adLogin/";
	
	@Autowired
	private ADLoginFormValidator validator;
	@Autowired
	private ADLoginService adLoginService;
	
	@GetMapping("/index")
	public String index(HttpServletRequest request, ADLoginForm form, Model model) {
		try {
			//已經登入
			Admin admin = SessionUtil.getAdmin(request);
			if(Optional.ofNullable(admin).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_IS_LONGINING);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			model.addAttribute("ADLoginForm", form);
			return  prefix + "index";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
			return "common/jsAlert";
		}
	}
	
	
	@PostMapping("/doLogin")
	public String doLogin(HttpServletRequest request, ADLoginForm form, BindingResult result, Model model){
		try {
			//已經登入
			Admin admin = SessionUtil.getAdmin(request);
			Optional<Admin> adminOptional = Optional.ofNullable(admin);
			if(adminOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_IS_LONGINING);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				return prefix + "index";
			}
			adminOptional = adLoginService.login(form.getAccount(), form.getPassword());
			//查無資料
			if(!adminOptional.isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_ADMIN_ACCOUNT_PASSWORD_ERROR);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			SessionUtil.setAdmin(request, adminOptional.get());
			model.addAttribute("message", MessageConstants.MESSAGE_LOGIN_SUCCESS);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/signOut")
	public String signOut(HttpServletRequest request, ADLoginForm form, Model model) {
		try {
			SessionUtil.removeAdmin(request);
			model.addAttribute("message", MessageConstants.MESSAGE_SIGN_OUT);
			model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
			return "common/jsAlert";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
			return "common/jsAlert";
		}
	}
}
