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
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryBuilder;
import com.front.chuchuPurchasingAgent.Models.Country;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.CountryRepository;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	public Optional<Country> findOne(Country country){
		Example<Country> example = Example.of(country); 
		return countryRepository.findOne(example);
	}
	
	/**
	 * 查全部
	 * @return
	 * @throws Exception
	 */
	public List<Country> findAll(Country country) throws Exception {
		Example<Country> example = Example.of(country); 
		return countryRepository.findAll(example);		
	}
	/**
	 * 產生國家Map(依照地區分)
	 * @param countrys
	 * @return
	 */
	public Map<String, List<Country>> getCountriesMap(List<Country> countries){
		Map<String, List<Country>> map = new LinkedHashMap<>();
		String key = "";
		List<Country> temp = new ArrayList<>();
		for(Country entity : countries) {
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
	public Map<String, String> getCountryIdMap(List<Country> countries){
		Map<String, String> map = new HashMap<>();
		for(Country country : countries) {
			map.put(country.getId(), country.getName());
		}
		return map;
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<Country> findPage(String areaId, String webStatus, String storehouseStatus, PageParameter pageParameter){
		Specification<Country> specification = new Specification<Country>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				Predicate[] predicates = new Predicate[predicatesList.size()];
				if(StringUtils.isNoneBlank(areaId)) {
					Predicate areaIdPredicate = criteriaBuilder.equal(root.get("areaId"), areaId);
					predicatesList.add(areaIdPredicate);
				}
				if(StringUtils.isNoneBlank(webStatus)) {
					Predicate statusPredicate = criteriaBuilder.equal(root.get("webStatus"), webStatus);
					predicatesList.add(statusPredicate);
				}
				if(StringUtils.isNoneBlank(storehouseStatus)) {
					Predicate statusPredicate = criteriaBuilder.equal(root.get("storehouseStatus"), storehouseStatus);
					predicatesList.add(statusPredicate);
				}
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
		return countryRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public Country formToModal(Form form) {
		Country country = new CountryBuilder().build();
		BeanUtils.copyProperties(form, country);
		return country;
	}
	/**
	 * 選擇儲存方式
	 * @param country
	 * @param adminId
	 */
	public void selectSaveType(Country country, String adminId) {
		if(!Optional.ofNullable(country.getId()).isPresent()) {				
			this.insertSetting(country, adminId);
		}else {
			this.updateSetting(country, adminId);
		}
	}
	/**
	 * 儲存
	 * @param country
	 */
	public void save(Country country) {
		countryRepository.save(country);
	}
	/**
	 * 寫入設定
	 * @param country
	 * @param adminId
	 */
	private void insertSetting(Country country, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CountryBuilder(country)
		.buildId(this.getNewId())
		.buildCreateTime(now)
		.buildCreator(adminId)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}
	/**
	 *更新設定
	 * @param country
	 * @param adminId
	 */
	private void updateSetting(Country country, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CountryBuilder(country)
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
		Optional<Country> currencyOptional = this.findTopByOrderByIdDesc();
		String id = currencyOptional.isPresent() ? currencyOptional.get().getId() : "0";
		return Integer.parseInt(id);
	}
	/**
	 * 取得最後一個Currency
	 * @return
	 */
	private Optional<Country> findTopByOrderByIdDesc() {
		return countryRepository.findTopByOrderByIdDesc();
	}
}
