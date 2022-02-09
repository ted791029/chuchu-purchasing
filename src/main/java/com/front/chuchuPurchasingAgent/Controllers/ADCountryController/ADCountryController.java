package com.front.chuchuPurchasingAgent.Controllers.ADCountryController;

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
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.CountryArea;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Services.CountryAreaService;
import com.front.chuchuPurchasingAgent.Services.CountryService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;
import com.front.chuchuPurchasingAgent.tool.util.StatusUtil;

@Controller
@RequestMapping("/chuAdmin/country")
public class ADCountryController {
	
	private String prefix = "adCountry/";
	@Autowired
	private CountryService countryService;
	@Autowired
	private CountryAreaService countryAreaService;
	@Autowired
	private ADCountryFormValidator validator;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADCountryForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			
			Page<Country> page = countryService.findPage(form.getAreaId(), form.getWebStatus(), form.getStorehouseStatus(), pageParameter);
			//取得所有地區
			CountryArea countryAreaEx = new CountryAreaBuilder().build();
			model.addAttribute("countryAreaIdMap", countryAreaService.getCountryAreaIdMap(countryAreaService.findAll(countryAreaEx)));
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
	public String add(HttpServletRequest request, ADCountryForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			//取得所有啟用地區
			CountryArea countryAreaEx = new CountryAreaBuilder().buildStatus(Constants.STATUS_ENABLE).build();
			model.addAttribute("countryAreaIdMap", countryAreaService.getCountryAreaIdMap(countryAreaService.findAll(countryAreaEx)));
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
	public String save(HttpServletRequest request, ADCountryForm form, BindingResult result, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				CountryArea countryAreaEx = new CountryAreaBuilder().buildStatus(Constants.STATUS_ENABLE).build();
				model.addAttribute("countryAreaIdMap", countryAreaService.getCountryAreaIdMap(countryAreaService.findAll(countryAreaEx)));
				return prefix + form.getUrlSuffix();
			}
			Country country = countryService.formToModal(form);
			countryService.selectSaveType(country, admin.getName());
			countryService.save(country);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/country/find");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, ADCountryForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Country countryEx = new CountryBuilder()
					.buildId(form.getId())
					.build();
			Optional<Country> countryOptional = countryService.findOne(countryEx);
			if(!countryOptional.isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_COUNTRY_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			BeanUtils.copyProperties(countryOptional.get(), form);
			//取得所有地區
			CountryArea countryAreaEx = new CountryAreaBuilder().build();
			model.addAttribute("countryAreaIdMap", countryAreaService.getCountryAreaIdMap(countryAreaService.findAll(countryAreaEx)));
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
