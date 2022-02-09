package com.front.chuchuPurchasingAgent.Controllers.OrderController;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.MessageConstants;
import com.front.chuchuPurchasingAgent.ModelBuilders.BankBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.MyOrderBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.OrderProductBuilder;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteBuilder;
import com.front.chuchuPurchasingAgent.Models.Bank;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.Currency;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.Models.MyOrder;
import com.front.chuchuPurchasingAgent.Models.OrderProduct;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRoute;
import com.front.chuchuPurchasingAgent.Services.BankService;
import com.front.chuchuPurchasingAgent.Services.CountryService;
import com.front.chuchuPurchasingAgent.Services.CurrencyService;
import com.front.chuchuPurchasingAgent.Services.OrderProductService;
import com.front.chuchuPurchasingAgent.Services.OrderService;
import com.front.chuchuPurchasingAgent.Services.ParcelRouteService;
import com.front.chuchuPurchasingAgent.tool.util.LogUtil;
import com.front.chuchuPurchasingAgent.tool.util.SessionUtil;

@Controller
@RequestMapping("/front/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderProductService orderProductService; 
	@Autowired
	private CountryService countryService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private BankService bankService;
	@Autowired
	private ParcelRouteService parcelRouteService;
	
	private String prefix = "order/";
	
	@PostMapping("/save")
	public String save(HttpServletRequest request, OrderForm form, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			if(!Optional.ofNullable(member).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			MyOrder myOrder = orderService.formToModel(form);
			List<OrderProduct> products = form.getProducts();
			orderService.save(member.getId(), myOrder, products);
			model.addAttribute("orderForm", form);
			model.addAttribute("member", member);
			model.addAttribute("message", MessageConstants.MESSAGE_ORDER_SUCCESS);
			model.addAttribute("relativeUrl", Constants.ORDER_VIEW_URI + "?id="  + myOrder.getId());
			return "common/jsAlert";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}
	
	@GetMapping("/view")
	public String view(HttpServletRequest request, OrderForm form, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			if(!Optional.ofNullable(member).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			model.addAttribute("member", member);
			MyOrder orderEx = new MyOrderBuilder()
					.buildId(form.getId())
					.build();
			Optional<MyOrder> orderOptional = orderService.findOne(orderEx);
			if(!orderOptional.isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_ORDER_NOT_FOUND);
				model.addAttribute("relativeUrl", Constants.INDEX_URI);
				return "common/jsAlert";
			}
			MyOrder order = orderOptional.get();
			BeanUtils.copyProperties(order, form);
			model.addAttribute("orderForm", form);
			//Procducts
			OrderProduct orderProductEx = new OrderProductBuilder()
					.buildOrderId(order.getId())
					.build();
			List<OrderProduct> products = orderProductService.findAll(orderProductEx);
			form.setProducts(products);
			//取得所有國家
			Country countryEx = new CountryBuilder().build();
			model.addAttribute("countryIdMap", countryService.getCountryIdMap(countryService.findAll(countryEx)));
			//取得所有銀行
			Bank bankEx = new BankBuilder().build();
			model.addAttribute("bankIdMap", bankService.getBankIdMap(bankService.findAll(bankEx)));
			model.addAttribute("bankAccountMap", bankService.getBankAccountMap(bankService.findAll(bankEx)));
			//取得所有幣別
			Currency currencyEx = new CurrencyBuilder().build();
			model.addAttribute("currencyIdMap", currencyService.getCurrencyIdMap(currencyService.findAll(currencyEx)));
			//取得所有啟用的包裹航線
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
	public String find(HttpServletRequest request, OrderFindForm form, PageParameter pageParameter, Model model) {
		try {
			Member member = SessionUtil.getMember(request);
			if(!Optional.ofNullable(member).isPresent()) {
				model.addAttribute("message", MessageConstants.MESSAGE_NOT_LONGIN);
				model.addAttribute("relativeUrl", Constants.LOGIN_URI);
				return "common/jsAlert";
			}
			model.addAttribute("member", member);
			Page<MyOrder> page = orderService.findPageByMemberId(member.getId(), form.getStart(), form.getEnd(), pageParameter);
			model.addAttribute("page", page);
			model.addAttribute("orderFindForm", form);
			return prefix +  "find";
		} catch (Exception e) {
			LogUtil.setErrorLog("info", e);
			model.addAttribute("message", MessageConstants.MESSAGE_SYSTEM_BUSY);
			model.addAttribute("relativeUrl", Constants.INDEX_URI);
			return "common/jsAlert";
		}
	}

}
