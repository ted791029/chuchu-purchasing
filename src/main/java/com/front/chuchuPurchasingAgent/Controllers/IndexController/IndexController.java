package com.front.chuchuPurchasingAgent.Controllers.IndexController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.MessageConstants;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/front")
public class IndexController {
	@GetMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			model.addAttribute("member", member);
			return  "index";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
}
