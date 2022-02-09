package com.front.chuchuPurchasingAgent.Controllers.QueryController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.MessageConstants;
import com.front.chuchuPurchasingAgent.JoinModel.ParcelRouteJoinCountry;
import com.front.chuchuPurchasingAgent.ModelBuilders.BankBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryAreaBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.LocalFeeBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteNoteBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteOfCountryBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.PurchasingRateBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.QeryProductBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.QueryBuilder;
import com.front.chuchuPurchasingAgent.Models.Bank;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.CountryArea;
import com.front.chuchuPurchasingAgent.Models.Currency;
import com.front.chuchuPurchasingAgent.Models.LocalFee;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRoute;
import com.front.chuchuPurchasingAgent.Models.ParcelRouteNote;
import com.front.chuchuPurchasingAgent.Models.ParcelRouteOfCountry;
import com.front.chuchuPurchasingAgent.Models.PurchasingRate;
import com.front.chuchuPurchasingAgent.Models.Query;
import com.front.chuchuPurchasingAgent.Models.QueryProduct;
import com.front.chuchuPurchasingAgent.Services.BankService;
import com.front.chuchuPurchasingAgent.Services.CountryAreaService;
import com.front.chuchuPurchasingAgent.Services.CountryService;
import com.front.chuchuPurchasingAgent.Services.CurrencyService;
import com.front.chuchuPurchasingAgent.Services.CustomerSourceService;
import com.front.chuchuPurchasingAgent.Services.LocalFeeService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteNoteService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteOfCountryService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteService;
import com.front.chuchuPurchasingAgent.Services.PurchasingRateService;
import com.front.chuchuPurchasingAgent.Services.QueryProductSerivce;
import com.front.chuchuPurchasingAgent.Services.QueryService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;


@Controller
@RequestMapping("/front/query")
public class QueryController {
	private String prefix = "query/";
	@Autowired
	private QueryService queryService;
	@Autowired
	private CountryAreaService countryAreaService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private BankService bankService;
	@Autowired
	private ParcelRouteService parcelRouteService;
	@Autowired
	private QueryProductSerivce queryProductSerivce;
	@Autowired
	private CustomerSourceService customerSourceService;
	@Autowired
	private PurchasingRateService purchasingRateService;
	@Autowired
	private LocalFeeService localFeeService;
	@Autowired
	private ParcelRouteOfCountryService parcelRouteOfCountryService;
	@Autowired
	private ParcelRouteNoteService parvelRouteNoteService;
	
	
	@GetMapping("/add")
	public String add(HttpServletRequest request, QueryForm form, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			if(Optional.ofNullable(member).isPresent()) form.setEmail(member.getEmail());
			//取得所有啟用的地區
			CountryArea countryAreaEx = new CountryAreaBuilder().buildStatus(Constants.STATUS_ENABLE).build();
			model.addAttribute("countryAreas", countryAreaService.findAll(countryAreaEx));
			//取得所有啟用的銀行
			Bank bankEx = new BankBuilder().buildStatus(Constants.STATUS_ENABLE).build();
			model.addAttribute("banks", bankService.findAll(bankEx));
			model.addAttribute("queryForm", form);
			model.addAttribute("member", member);
			return prefix +  "add";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	@PostMapping("/save")
	public String save(HttpServletRequest request, QueryForm form, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			Query query = queryService.formToModel(form);
			if(Optional.ofNullable(member).isPresent()) new QueryBuilder(query).buildMemberId(member.getId());
			queryService.insertSetting(query);
			List<QueryProduct> products = form.getProducts();
			queryService.save(query, products);
			model.addAttribute("member", member);
			model.addAttribute("message", MessageConstants.MESSAGE_QUERY_SUCCESS);
			model.addAttribute("relativeUrl", Constants.QUERY_VIEW_URI + "?id="  + query.getId());
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/view")
	public String view(HttpServletRequest request, QueryViewForm form, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			model.addAttribute("member", member);
			Query queryEx = new QueryBuilder()
					.buildId(form.getId())
					.build();
			Optional<Query> queryOptional = queryService.findOne(queryEx);
			if(!queryOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_QUERY_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.INDEX_URI);
				return "common/jsAlert";
			}
			//Query
			Query query = queryOptional.get();
			BeanUtils.copyProperties(query, form);
			model.addAttribute("queryViewForm", form);
			//Procducts
			QueryProduct productEx = new QeryProductBuilder().buildQueryId(query.getId()).build();
			List<QueryProduct> products = queryProductSerivce.findAll(productEx);
			queryProductSerivce.setProudctRateAndTotal(query, products);
			form.setProducts(products);
			int allTotalPrice = queryProductSerivce.getAllTotalPrice(products);
			form.setAllTotalPrice(allTotalPrice);
			//PurchasingRate
			String whereId = customerSourceService.getSourceId(query.getDiscountCode());
			PurchasingRate purchasingRateEx = new PurchasingRateBuilder()
					.buildWhereId(whereId)
					.buildCountryId(query.getCountryId())
					.build();
			Optional<PurchasingRate> purchasingRateOptional = purchasingRateService.findOne(purchasingRateEx);
			if(!purchasingRateOptional.isPresent()){
				//TODO 印log
				model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
				model.addAttribute("relativeUrl", Constants.INDEX_URI);
				return "common/jsAlert";
			}
			int purchasingPrice = purchasingRateService.getPurchasingPrice(allTotalPrice, purchasingRateOptional);
			form.setPurchasingPrice(purchasingPrice);
			//LocalFee
			//取得所有手續費設定
			LocalFee localFeeEx = new LocalFeeBuilder().build();
			List<LocalFee> localFees = localFeeService.findAll(localFeeEx);
			int localFeePrice = localFeeService.getLocalFeePrice(products, localFees);
			form.setLocalFeePrice(localFeePrice);
			//fristStagePrice
			form.setFirstStagePrice(allTotalPrice + purchasingPrice + localFeePrice);
			//取得所有網站的國家
			Country countryEx = new CountryBuilder().build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
			//取得所有銀行
			Bank bankEx = new BankBuilder().build();
			model.addAttribute("bankIdMap", bankService.getBankIdMap(bankService.findAll(bankEx)));
			//取得所有幣別
			Currency currencyEx = new CurrencyBuilder().build();
			model.addAttribute("currencyIdMap", currencyService.getCurrencyIdMap(currencyService.findAll(currencyEx)));
			//取得所有包裹航線
			ParcelRoute parcelRouteEx = new ParcelRouteBuilder().build();
			model.addAttribute("parcelRouteIdMap", parcelRouteService.getParcelRouteIdMap(parcelRouteService.findAll(parcelRouteEx)));
			return prefix +  "view";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request, QueryFindForm form, PageParameter pageParameter, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			Page<Query> page = null;
			if(StringUtils.isNoneBlank(form.getEmail())) page = queryService.findPageByEmail(form.getEmail(), form.getStart(), form.getEnd(), pageParameter);
			model.addAttribute("page", page);
			model.addAttribute("member", member);
			model.addAttribute("queryFindForm", form);
			return prefix +  "find";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@RequestMapping("/myQuery")
	public String myQuery(HttpServletRequest request, MyQueryForm form, PageParameter pageParameter, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			if(!Optional.ofNullable(member).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			Page<Query> page = queryService.findPageByMemberId(member.getId(), form.getStart(), form.getEnd(), pageParameter);
			model.addAttribute("page", page);
			model.addAttribute("member", member);
			model.addAttribute("queryFindForm", form);
			return prefix +  "myQuery";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@ResponseBody
	@RequestMapping("/ajaxGetWebCountriesMap")
	/**
	 * 取得網站國家Map
	 * @return 
	 */
	public Map<String, List<Country>> ajaxGetWebCountriesMap(){
		try {
			//取得所有啟用的國家
			Country countryEx = new CountryBuilder().buildWebStatus(Constants.STATUS_ENABLE).build();
			return countryService.getCountriesMap(countryService.findAll(countryEx));
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/ajaxGetStorehouseCountriesMap")
	/**
	 * 取得倉庫Map
	 * @return 
	 */
	public Map<String, List<Country>> ajaxGetStorehouseCountriesMap(){
		try {
			//取得所有啟用的國家
			Country countryEx = new CountryBuilder().buildStorehouseStatus(Constants.STATUS_ENABLE).build();
			return countryService.getCountriesMap(countryService.findAll(countryEx));
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/ajaxGetCurreniesMap")
	/**
	 * 取得幣別map
	 * @return
	 */
	public Map<String, List<Currency>> ajaxGetCurreniesMap(){
		try {
			//取得所有啟用的幣別
			Currency currencyEx = new CurrencyBuilder().buildStatus(Constants.STATUS_ENABLE).build();
			return currencyService.getCurrenciesMap(currencyService.findAll(currencyEx));
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			return null;
		}
	}
	@ResponseBody
	@RequestMapping("/ajaxGetRoutesMap")
	/**
	 * 取得路線map
	 * @return
	 */
	public Map<String, List<ParcelRouteJoinCountry>> ajaxGetRoutesMap(){
		try {
			//取得所有有啟用的國家航線
			List<ParcelRouteJoinCountry> routes = parcelRouteService.getAllParcelRoutofCountries(Constants.STATUS_ENABLE, Constants.STATUS_ENABLE);
			return parcelRouteService.getParcelRouteMap(routes);
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("/ajaxGetQueryPage")
	/**
	 * 取得詢問單頁面
	 * @return 
	 */
	public Map<String, List<Country>> ajaxGetQueryPage(){
		try {
			//取得所有啟用的國家
			Country countryEx = new CountryBuilder().buildWebStatus(Constants.STATUS_ENABLE).build();
			return countryService.getCountriesMap(countryService.findAll(countryEx));
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("/ajaxGetRouteNote")
	/**
	 * 取得運送備註
	 * @return 
	 */
	public ParcelRouteNote ajaxGetRouteNote(@RequestParam(value="discountCode",required=true) String discountCode,
			@RequestParam(value="storehouseCountryId",required=true) String storehouseCountryId,
			@RequestParam(value="parcelRouteId",required=true) String parcelRouteId){
		try {
			String whereId = customerSourceService.getSourceId(discountCode);
			ParcelRouteOfCountry parcelRouteOfCountryEx = new ParcelRouteOfCountryBuilder()
					.buildCountryId(storehouseCountryId)
					.buildRouteId(parcelRouteId)
					.build();
			Optional<ParcelRouteOfCountry> parcelRouteOfCountryOptional = parcelRouteOfCountryService.findOne(parcelRouteOfCountryEx);
			if(!parcelRouteOfCountryOptional.isPresent()) return null;
			ParcelRouteNote parcelRouteNoteEx = new ParcelRouteNoteBuilder()
					.buildFromWhereId(whereId)
					.buildCountryParcelRouteId(parcelRouteOfCountryOptional.get().getId())
					.build();
			Optional<ParcelRouteNote> parcelRouteNoteOptional = parvelRouteNoteService.findOne(parcelRouteNoteEx);
			return parcelRouteNoteOptional.isPresent() ? parcelRouteNoteOptional.get() : null;
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			return null;
		}
	}
}
