package com.front.chuchuPurchasingAgent.Controllers.ADAreaController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.MessageConstants;
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryAreaBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.CountryArea;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Services.CountryAreaService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;
import com.front.chuchuPurchasingAgent.tool.util.StatusUtil;

@Controller
@RequestMapping("/chuAdmin/countryArea")
public class ADCountryAreaController {
	
	private String prefix = "adCountryArea/";
	@Autowired
	private CountryAreaService countryAreaService;
	@Autowired
	private ADCountryAreaFormValidator validator;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADCountryAreaForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			
			Page<CountryArea> page = countryAreaService.findPage(form.getStatus(), pageParameter);
			model.addAttribute("statusMap", StatusUtil.getStatusMap());
			model.addAttribute("admin", admin);
			model.addAttribute("page", page);
			return  prefix + "find";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/add")
	public String add(HttpServletRequest request, ADCountryAreaForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			model.addAttribute("admin", admin);
			form.setUrlSuffix("add");
			return  prefix + "add";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@PostMapping("/save")
	public String save(HttpServletRequest request, ADCountryAreaForm form, BindingResult result, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				return prefix + form.getUrlSuffix();
			}
			CountryArea countryArea = countryAreaService.formToModal(form);
			countryAreaService.selectSaveType(countryArea, admin.getName());
			countryAreaService.save(countryArea);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/countryArea/find");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, ADCountryAreaForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			CountryArea countryAreaEx = new CountryAreaBuilder()
					.buildId(form.getId())
					.build();
			Optional<CountryArea> countryAreaOptional = countryAreaService.findOne(countryAreaEx);
			if(!countryAreaOptional.isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_AREA_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			BeanUtils.copyProperties(countryAreaOptional.get(), form);
			model.addAttribute("admin", admin);
			form.setUrlSuffix("edit");
			return  prefix + "edit";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
}
