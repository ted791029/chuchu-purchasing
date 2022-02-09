package com.front.chuchuPurchasingAgent.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyBuilder;
import com.front.chuchuPurchasingAgent.Models.Currency;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.CurrencyRepository;

@Service
public class CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepository;
	
	public Optional<Currency> findOne(Currency currency){
		Example<Currency> example = Example.of(currency); 
		return currencyRepository.findOne(example);		
	}
	/**
	 * 查全部
	 * @return
	 * @throws Exception
	 */
	public List<Currency> findAll(Currency currency) throws Exception {
		Example<Currency> example = Example.of(currency); 
		return currencyRepository.findAll(example);		
	}
	/**
	 * 產生國家Map(依照地區分)
	 * @param countrys
	 * @return
	 */
	public Map<String, List<Currency>> getCurrenciesMap(List<Currency> currencies){
		Map<String, List<Currency>> map = new LinkedHashMap<>();
		String key = "";
		List<Currency> temp = new ArrayList<>();
		for(Currency entity : currencies) {
			key = entity.getAreaId();
			if(map.containsKey(key)) {
				temp = map.get(key);
				temp.add(entity);
				continue;
			}
			temp = new ArrayList<>();
			temp.add(entity);
			map.put(key, temp);
		}
		return map;
	}
	
	/**
	 * 取得ID對照Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getCurrencyIdMap(List<Currency> currencies){
		Map<String, String> map = new HashMap<>();
		for(Currency currency : currencies) {
			map.put(currency.getId(), currency.getName());
		}
		return map;
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<Currency> findPage(String areaId, String status, PageParameter pageParameter){
		Specification<Currency> specification = new Specification<Currency>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Currency> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				if(StringUtils.isNoneBlank(areaId)) {
					Predicate areaIdPredicate = criteriaBuilder.equal(root.get("areaId"), areaId);
					predicatesList.add(areaIdPredicate);
				}
				if(StringUtils.isNoneBlank(status)) {
					Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
					predicatesList.add(statusPredicate);
				}
				Predicate[] predicates = new Predicate[predicatesList.size()];
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.asc(root.get("areaId")));
				orders.add(criteriaBuilder.asc(root.get("id")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return currencyRepository.findAll(specification, pageable);
	}
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public Currency formToModal(Form form) {
		Currency currency = new CurrencyBuilder().build();
		BeanUtils.copyProperties(form, currency);
		return currency;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(Currency currency, String adminId) {
		if(!Optional.ofNullable(currency.getId()).isPresent()) {				
			this.insertSetting(currency, adminId);
		}else {
			this.updateSetting(currency, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(Currency currency) {
		currencyRepository.save(currency);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(Currency currency, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CurrencyBuilder(currency)
		.buildId(this.getNewId())
		.buildCreateTime(now)
		.buildCreator(adminId)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}
	/**
	 *更新設定
	 * @param currency
	 * @param adminId
	 */
	private void updateSetting(Currency currency, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CurrencyBuilder(currency)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}
	/**
	 * 取得最新ID
	 * @return
	 */
	private String getNewId() {
		int number = this.getLastIdNumber() + 1;
		if(number < 10) {
			return "00" + String.valueOf(number);
		}else if(number < 100){
			return "0" + String.valueOf(number);
		}else {
			return String.valueOf(number);
		}
	}
	/**
	 * 取得最後一個id的數字
	 * @return
	 */
	private Integer getLastIdNumber() {
		Optional<Currency> currencyOptional = this.findTopByOrderByIdDesc();
		String id = currencyOptional.isPresent() ? currencyOptional.get().getId() : "0";
		return Integer.parseInt(id);
	}
	/**
	 * 取得最後一個Currency
	 * @return
	 */
	private Optional<Currency> findTopByOrderByIdDesc() {
		return currencyRepository.findTopByOrderByIdDesc();
	}
}
