package com.front.chuchuPurchasingAgent.Controllers.ADPracelRouteNoteController;

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
import com.front.chuchuPurchasingAgent.Controllers.ADParcelRouteController.ADParcelRouteForm;
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.FromWhereBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteNoteBuilder;
import com.front.chuchuPurchasingAgent.Models.Admin;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.FromWhere;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRoute;
import com.front.chuchuPurchasingAgent.Models.ParcelRouteNote;
import com.front.chuchuPurchasingAgent.Services.CountryService;
import com.front.chuchuPurchasingAgent.Services.FromWhereService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteNoteService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/chuAdmin/parcelRouteNote")
public class ADParcelRouteNoteController {
	private String prefix = "adParcelRouteNote/";
	@Autowired
	private ParcelRouteNoteService parcelRouteNoteService;
	@Autowired
	private FromWhereService fromWhereService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private ParcelRouteService parcelRouteService;
	@Autowired
	private ADParcelRouteNoteValidator validator;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, ADParcelRouteNoteForm form, PageParameter pageParameter, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			Page<ParcelRouteNote> page = parcelRouteNoteService.findPage(form.getCountryParcelRouteId(),pageParameter);
			//取得所有來源種類
			FromWhere fromWhereEx = new FromWhereBuilder().build();
			model.addAttribute("fromWhereIdMap", fromWhereService.getFromWhereIdMap(fromWhereService.findAll(fromWhereEx)));
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
	public String add(HttpServletRequest request, ADParcelRouteNoteForm form, Model model) {
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
			//取得所有國家
			Country countryEx = new CountryBuilder().build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
			//取得所有運送方式
			ParcelRoute parcelRouteEx = new ParcelRouteBuilder().build();
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
	public String save(HttpServletRequest request, ADParcelRouteNoteForm form, BindingResult result, Model model) {
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
				//取得所有國家
				Country countryEx = new CountryBuilder().build();
				model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
				//取得所有運送方式
				ParcelRoute parcelRouteEx = new ParcelRouteBuilder().build();
				model.addAttribute("routeIdMap", parcelRouteService.getParcelRouteIdMap(parcelRouteService.findAll(parcelRouteEx)));
				return prefix + form.getUrlSuffix();
			}
			ParcelRouteNote parcelRouteNote = parcelRouteNoteService.formToModal(form);
			parcelRouteNoteService.selectSaveType(parcelRouteNote, admin.getName());
			parcelRouteNoteService.save(parcelRouteNote);
			model.addAttribute("message", MessageConstants.MESSAGE_SAVE_SUCCESS);
			String url = "/chuAdmin/parcelRouteNote/find?countryParcelRouteId=" + form.getCountryParcelRouteId() + "\\&countryId=" + form.getCountryId() + "\\&routeId=" + form.getRouteId(); 
			model.addAttribute("relativeUrl", url);
			LogUtil.setInfoLog("parcelRouteNote id: " + parcelRouteNote.getId() + " save success");
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, ADParcelRouteNoteForm form, Model model) {
		try {
			Admin admin = SessionUtil.getAdmin(request);
			if(!Optional.ofNullable(admin).isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.ADMIN_LOGIN_URI);
				return "common/jsAlert";
			}
			ParcelRouteNote parcelRouteNoteEx = new ParcelRouteNoteBuilder()
					.buildId(form.getId())
					.build();
			Optional<ParcelRouteNote> parcelRouteNoteOptional = parcelRouteNoteService.findOne(parcelRouteNoteEx);
			if(!parcelRouteNoteOptional.isPresent()) {				
				model.addAttribute("message", MessageConstants.MESSAGE_PARCEL_ROUTE_NOTE_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.ADMIN_INTRO_INDEX_URI);
				return "common/jsAlert";
			}
			BeanUtils.copyProperties(parcelRouteNoteOptional.get(), form);
			//取得所有啟用的來源種類
			FromWhere fromWhereEx = new FromWhereBuilder()
					.buildStatus(Constants.STATUS_ENABLE)
					.build();
			model.addAttribute("fromWhereIdMap", fromWhereService.getFromWhereIdMap(fromWhereService.findAll(fromWhereEx)));
			//取得所有國家
			Country countryEx = new CountryBuilder().build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
			//取得所有運送方式
			ParcelRoute parcelRouteEx = new ParcelRouteBuilder().build();
			model.addAttribute("routeIdMap", parcelRouteService.getParcelRouteIdMap(parcelRouteService.findAll(parcelRouteEx)));
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
