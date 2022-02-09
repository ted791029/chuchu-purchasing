package com.front.chuchuPurchasingAgent.Controllers.MemberController;

import java.util.Optional;

import javax.management.AttributeNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.MessageConstants;
import com.front.chuchuPurchasingAgent.ModelBuilders.MemberBuilder;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.Services.MemberService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/front/member")
public class MemebrController {
	private String prefix = "member/";
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberFormValidator validator;
	
	/**
	 * 會員中心
	 * @param form
	 * @param model
	 * @return
	 * @throws AttributeNotFoundException
	 */
	@GetMapping("/index")
	public String index(HttpServletRequest request, MemberForm form, Model model) throws AttributeNotFoundException {
		try {
			Member sessionMember = SessionUtil.getMember(request);
			if(!Optional.ofNullable(sessionMember).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			Member memberEx = new MemberBuilder()
					.buildId(sessionMember.getId())
					.build();
			BeanUtils.copyProperties(memberService.findOne(memberEx).get(), form);
			model.addAttribute("memberForm", form);
			return  prefix + "index";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, MemberForm form, Model model) throws AttributeNotFoundException {
		try {
			Member sessionMember = SessionUtil.getMember(request);
			if(!Optional.ofNullable(sessionMember).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			Member memberEx = new MemberBuilder()
					.buildId(sessionMember.getId())
					.build();
			BeanUtils.copyProperties(memberService.findOne(memberEx).get(), form);
			return  prefix + "edit";
		}catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@PostMapping("/save")
	public String save(HttpServletRequest request, MemberForm form, BindingResult result, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			if(!Optional.ofNullable(member).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				return prefix + "edit";
			}
			Member memberEx = memberService.formToModel(form);
			memberService.updateSetting(memberEx);
			memberService.updateInfo(memberEx);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/front/member/index");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}

}
