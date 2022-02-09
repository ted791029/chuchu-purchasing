package com.front.chuchuPurchasingAgent.Controllers.ADCurrencyController;

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
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.CountryArea;
import com.front.chuchuPurchasingAgent.Models.Currency;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Services.CountryAreaService;
import com.front.chuchuPurchasingAgent.Services.CurrencyService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;
import com.front.chuchuPurchasingAgent.tool.util.StatusUtil;

@Controller
@RequestMapping("/chuAdmin/currency")
public class ADCurrencyController {
	
	private String prefix = "adCurrency/";
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CountryAreaService countryAreaService;
	@Autowired
	private ADCurrencyFormValidator validator;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADCurrencyForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Page<Currency> page = currencyService.findPage(form.getAreaId(), form.getStatus(),pageParameter);
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
	public String add(HttpServletRequest request, ADCurrencyForm form, Model model) {
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
	public String save(HttpServletRequest request, ADCurrencyForm form, BindingResult result, Model model) {
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
			Currency currency = currencyService.formToModal(form);
			currencyService.selectSaveType(currency, admin.getName());
			currencyService.save(currency);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/currency/find");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, ADCurrencyForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Currency currencyEx = new CurrencyBuilder()
					.buildId(form.getId())
					.build();
			Optional<Currency> currencyOptional = currencyService.findOne(currencyEx);
			if(!currencyOptional.isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_CURRENCY_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			BeanUtils.copyProperties(currencyOptional.get(), form);
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
