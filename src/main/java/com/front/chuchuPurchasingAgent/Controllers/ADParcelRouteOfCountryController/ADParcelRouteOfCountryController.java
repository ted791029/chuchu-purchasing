package com.front.chuchuPurchasingAgent.Controllers.ADParcelRouteOfCountryController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteOfCountryBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRoute;
import com.front.chuchuPurchasingAgent.Models.ParcelRouteOfCountry;
import com.front.chuchuPurchasingAgent.Services.CountryService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteOfCountryService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/chuAdmin/parcelRouteOfCountry")
public class ADParcelRouteOfCountryController {
	
	private String prefix = "adParcelRouteOfCountry/";
	@Autowired
	private ParcelRouteOfCountryService parcelRouteOfCountryService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private ParcelRouteService parcelRouteService;
	@Autowired
	private ADParcelRouteOfCountryFormValidator validator;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADParcelRouteOfCountryForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Page<ParcelRouteOfCountry> page = parcelRouteOfCountryService.findPage(form.getCountryId(), form.getRouteId(), pageParameter);
			//取得所有國家
			Country countryEx = new CountryBuilder().build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
			//取得所有運送方式
			ParcelRoute parcelRouteEx = new ParcelRouteBuilder().build();
			model.addAttribute("routeIdMap", parcelRouteService.getParcelRouteIdMap(parcelRouteService.findAll(parcelRouteEx)));
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
	public String add(HttpServletRequest request, ADParcelRouteOfCountryForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			//取得所有倉庫啟用的國家
			Country countryEx = new CountryBuilder()
					.buildStorehouseStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
			//取得所有啟用的運送方式
			ParcelRoute parcelRouteEx = new ParcelRouteBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("routeIdMap", parcelRouteService.getParcelRouteIdMap(parcelRouteService.findAll(parcelRouteEx)));
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
	public String save(HttpServletRequest request, ADParcelRouteOfCountryForm form, BindingResult result, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			validator.validate(form, result);
			if (result.hasErrors()) {
				//取得所有倉庫啟用的國家
				Country countryEx = new CountryBuilder()
						.buildStorehouseStatus(Constants.STATUS_ENABLE)
						.build();
				model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
				//取得所有啟用的運送方式
				ParcelRoute parcelRouteEx = new ParcelRouteBuilder()
						.buildStatus(Constants.STATUS_ENABLE)
						.build();
				model.addAttribute("routeIdMap", parcelRouteService.getParcelRouteIdMap(parcelRouteService.findAll(parcelRouteEx)));
				return prefix + form.getUrlSuffix();
			}
			ParcelRouteOfCountry parcelRouteOfCountry = parcelRouteOfCountryService.formToModal(form);
			parcelRouteOfCountryService.selectSaveType(parcelRouteOfCountry, admin.getName());
			parcelRouteOfCountryService.save(parcelRouteOfCountry);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/parcelRouteOfCountry/find");
			LogUtil.setInfoLog("parcelRouteOfCountry id: " + parcelRouteOfCountry.getId() + " save success");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@PostMapping("/delete")
	public String delete(HttpServletRequest request, ADParcelRouteOfCountryForm form, BindingResult result, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			if(!Optional.ofNullable(form.getIds()).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			for(String id : form.getIds()) {
				ParcelRouteOfCountry parcelRouteOfCountryEx = new ParcelRouteOfCountryBuilder()
						.buildId(id)
						.build();				
				parcelRouteOfCountryService.delete(parcelRouteOfCountryEx);
				LogUtil.setInfoLog("parcelRouteOfCountry id: " + id + " delete success");
			}
			model.addAttribute("message", MessageConstants.MESSAGE_DELETE_SUCCESS);
			model.addAttribute("relativeUrl", "/chuAdmin/parcelRouteOfCountry/find");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
}
