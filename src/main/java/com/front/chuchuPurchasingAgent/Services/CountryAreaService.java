package com.front.chuchuPurchasingAgent.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import com.front.chuchuPurchasingAgent.ModelBuilders.CountryAreaBuilder;
import com.front.chuchuPurchasingAgent.Models.CountryArea;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.CountryAreaRepository;

@Service
public class CountryAreaService {
	@Autowired
	private CountryAreaRepository countryAreaRepository;
	/**
	 * 取單一
	 * @param countryArea
	 * @return
	 * @throws Exception
	 */
	public Optional<CountryArea> findOne(CountryArea countryArea) throws Exception {
		Example<CountryArea> example = Example.of(countryArea); 
		return countryAreaRepository.findOne(example);		
	}
	/**
	 * 查全部
	 * @return
	 * @throws Exception
	 */
	public List<CountryArea> findAll(CountryArea countryArea) throws Exception {
		Example<CountryArea> example = Example.of(countryArea); 
		return countryAreaRepository.findAll(example);		
	}
	
	/**
	 * 取得ID對照Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getCountryAreaIdMap(List<CountryArea> countryAreas){
		Map<String, String> map = new HashMap<>();
		for(CountryArea countryArea : countryAreas) {
			map.put(countryArea.getId(), countryArea.getName());
		}
		return map;
	}
	
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<CountryArea> findPage(String status, PageParameter pageParameter){
		Specification<CountryArea> specification = new Specification<CountryArea>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<CountryArea> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				Predicate[] predicates = new Predicate[predicatesList.size()];
				if(StringUtils.isNoneBlank(status)) {
					Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
					predicatesList.add(statusPredicate);
				}
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.asc(root.get("id")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return countryAreaRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public CountryArea formToModal(Form form) {
		CountryArea countryArea = new CountryAreaBuilder().build();
		BeanUtils.copyProperties(form, countryArea);
		return countryArea;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(CountryArea countryArea, String adminId) {
		if(!Optional.ofNullable(countryArea.getId()).isPresent()) {				
			this.insertSetting(countryArea, adminId);
		}else {
			this.updateSetting(countryArea, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(CountryArea countryArea) {
		countryAreaRepository.save(countryArea);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(CountryArea countryArea, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CountryAreaBuilder(countryArea)
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
	private void updateSetting(CountryArea countryArea, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CountryAreaBuilder(countryArea)
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
		Optional<CountryArea> countryAreaOptional = this.findTopByOrderByIdDesc();
		String id = countryAreaOptional.isPresent() ? countryAreaOptional.get().getId() : "0";
		return Integer.parseInt(id);
	}
	/**
	 * 取得最後一個CountryArea
	 * @return
	 */
	private Optional<CountryArea> findTopByOrderByIdDesc() {
		return countryAreaRepository.findTopByOrderByIdDesc();
	}
}
