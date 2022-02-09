package com.front.chuchuPurchasingAgent.Controllers.ADSettingRateController;

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
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CustomerTypeBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.FromWhereBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.SettingRateBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.CustomerType;
import com.front.chuchuPurchasingAgent.Models.FromWhere;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.SettingRate;
import com.front.chuchuPurchasingAgent.Services.CountryService;
import com.front.chuchuPurchasingAgent.Services.CustomerTypeForModelService;
import com.front.chuchuPurchasingAgent.Services.FromWhereService;
import com.front.chuchuPurchasingAgent.Services.SettingRateService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/chuAdmin/settingRate")
public class ADSettingRateController {
	
	private String prefix = "adSettingRate/";
	@Autowired
	private SettingRateService settingRateService;
	@Autowired
	private FromWhereService fromWhereService;
	@Autowired
	private CustomerTypeForModelService customerTypeForModelService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private ADSettingRateValidator validator;
	
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADSettingRateForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Page<SettingRate> page = settingRateService.findPage(form.getWhereId(), form.getCustomerTypeId(), form.getCountryId(), pageParameter);
			//取得所有來源種類
			FromWhere fromWhereEx = new FromWhereBuilder().build();
			model.addAttribute("fromWhereIdMap", fromWhereService.getFromWhereIdMap(fromWhereService.findAll(fromWhereEx)));
			//取得所有客戶種類
			CustomerType customerTypeEx = new CustomerTypeBuilder().build();
			model.addAttribute("customerTypeIdMap", customerTypeForModelService.getCustomerTypeIdMap(customerTypeForModelService.findAll(customerTypeEx)));
			//取得所有國家
			Country countryEx = new CountryBuilder().build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
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
	public String add(HttpServletRequest request, ADSettingRateForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			//取得所有啟用的來源種類
			FromWhere fromWhereEx = new FromWhereBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("fromWhereIdMap", fromWhereService.getFromWhereIdMap(fromWhereService.findAll(fromWhereEx)));
			//取得所有有啟用的客戶種類
			CustomerType customerTypeEx = new CustomerTypeBuilder()
				.buildStatus(Constants.STATUS_ENABLE)
				.build();
			model.addAttribute("customerTypeIdMap", customerTypeForModelService.getCustomerTypeIdMap(customerTypeForModelService.findAll(customerTypeEx)));
			//取得所有有啟用網站的國家
			Country countryEx = new CountryBuilder()
					.buildWebStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
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
	public String save(HttpServletRequest request, ADSettingRateForm form, BindingResult result, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				//取得所有啟用的來源種類
				FromWhere fromWhereEx = new FromWhereBuilder()
						.buildStatus(Constants.STATUS_ENABLE)
						.build();
				model.addAttribute("fromWhereIdMap", fromWhereService.getFromWhereIdMap(fromWhereService.findAll(fromWhereEx)));
				//取得所有有啟用的客戶種類
				CustomerType customerTypeEx = new CustomerTypeBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
				model.addAttribute("customerTypeIdMap", customerTypeForModelService.getCustomerTypeIdMap(customerTypeForModelService.findAll(customerTypeEx)));
				//取得所有有啟用網站的國家
				Country countryEx = new CountryBuilder()
						.buildWebStatus(Constants.STATUS_ENABLE)
						.build();
				model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
				return prefix + form.getUrlSuffix();
			}
			SettingRate settingRate = settingRateService.formToModal(form);
			settingRateService.selectSaveType(settingRate, admin.getName());
			settingRateService.save(settingRate);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/settingRate/find");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, ADSettingRateForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			SettingRate settingRateEx = new SettingRateBuilder()
					.buildId(form.getId())
					.build();
			Optional<SettingRate> settingRateOptional = settingRateService.findOne(settingRateEx);
			if(!settingRateOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_CURRENCY_RATE_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			BeanUtils.copyProperties(settingRateOptional.get(), form);
			//取得所有啟用的來源種類
			FromWhere fromWhereEx = new FromWhereBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("fromWhereIdMap", fromWhereService.getFromWhereIdMap(fromWhereService.findAll(fromWhereEx)));
			//取得所有有啟用的客戶種類
			CustomerType customerTypeEx = new CustomerTypeBuilder()
				.buildStatus(Constants.STATUS_ENABLE)
				.build();
			model.addAttribute("customerTypeIdMap", customerTypeForModelService.getCustomerTypeIdMap(customerTypeForModelService.findAll(customerTypeEx)));
			//取得所有有啟用網站的國家
			Country countryEx = new CountryBuilder()
					.buildWebStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
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
