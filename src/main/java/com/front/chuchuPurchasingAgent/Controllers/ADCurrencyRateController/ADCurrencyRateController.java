package com.front.chuchuPurchasingAgent.Controllers.ADCurrencyRateController;

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
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyRateBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.Currency;
import com.front.chuchuPurchasingAgent.Models.CurrencyRate;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Services.CurrencyRateService;
import com.front.chuchuPurchasingAgent.Services.CurrencyService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/chuAdmin/currencyRate")
public class ADCurrencyRateController {
	
	private String prefix = "adCurrencyRate/";
	
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CurrencyRateService currencyRateService;
	@Autowired
	private ADCurrencyRateFormValidator validator;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADCurrencyRateForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Page<CurrencyRate> page = currencyRateService.findPage(pageParameter);
			//取得所有幣別
			Currency currencyEx = new CurrencyBuilder()
					.build();
			model.addAttribute("currencyIdMap", currencyService.getCurrencyIdMap(currencyService.findAll(currencyEx)));
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
	public String add(HttpServletRequest request, ADCurrencyRateForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			//取得所有啟用的幣別
			Currency currencyEx = new CurrencyBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("currencyIdMap", currencyService.getCurrencyIdMap(currencyService.findAll(currencyEx)));
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
	public String save(HttpServletRequest request, ADCurrencyRateForm form, BindingResult result, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				//取得所有啟用的幣別
				Currency currencyEx = new CurrencyBuilder()
						.buildStatus(Constants.STATUS_ENABLE)
						.build();
				model.addAttribute("currencyIdMap", currencyService.getCurrencyIdMap(currencyService.findAll(currencyEx)));
				return prefix + form.getUrlSuffix();
			}
			CurrencyRate currencyRate = currencyRateService.formToModal(form);
			currencyRateService.selectSaveType(currencyRate, admin.getName());
			currencyRateService.save(currencyRate);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/currencyRate/find");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, ADCurrencyRateForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			CurrencyRate currencyRateEx = new CurrencyRateBuilder()
					.buildId(form.getId())
					.build();
			Optional<CurrencyRate> currencyRateOptional = currencyRateService.findOne(currencyRateEx);
			if(!currencyRateOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_CURRENCY_RATE_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			BeanUtils.copyProperties(currencyRateOptional.get(), form);
			//取得所有啟用的幣別
			Currency currencyEx = new CurrencyBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("currencyIdMap", currencyService.getCurrencyIdMap(currencyService.findAll(currencyEx)));
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
