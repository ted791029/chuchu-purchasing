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
import com.front.chuchuPurchasingAgent.JoinModel.ParcelRouteJoinCountry;
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteBuilder;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRoute;
import com.front.chuchuPurchasingAgent.Repositories.ParcelRouteRepository;

@Service
public class ParcelRouteService {
	@Autowired
	private ParcelRouteRepository parcelRouteRepository;
	/**
	 * 取單一
	 * @param parcelRoute
	 * @return
	 * @throws Exception
	 */
	public Optional<ParcelRoute> findOne(ParcelRoute parcelRoute) throws Exception {
		Example<ParcelRoute> example = Example.of(parcelRoute); 
		return parcelRouteRepository.findOne(example);		
	}
	/**
	 * 查全部
	 * @return
	 * @throws Exception
	 */
	public List<ParcelRoute> findAll(ParcelRoute parcelRoute) throws Exception {
		Example<ParcelRoute> example = Example.of(parcelRoute); 
		return parcelRouteRepository.findAll(example);		
	}
	/**
	 * 取得ID對照Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getParcelRouteIdMap(List<ParcelRoute> parcelRoutes){
		Map<String, String> map = new HashMap<>();
		for(ParcelRoute parcelRoute : parcelRoutes) {
			map.put(parcelRoute.getId(), parcelRoute.getName());
		}
		return map;
	}
	
	/**
	 * 取得路線Map(依照國家區分)
	 * @param countryAreas
	 * @return
	 */
	public Map<String, List<ParcelRouteJoinCountry>> getParcelRouteMap(List<ParcelRouteJoinCountry> parcelRoutes){
		Map<String, List<ParcelRouteJoinCountry>> map = new HashMap<>();
		List<ParcelRouteJoinCountry> temp;
		for(ParcelRouteJoinCountry entity : parcelRoutes) {
			String key = entity.getCountryId();
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
	
	public List<ParcelRouteJoinCountry> getAllParcelRoutofCountries(String countryStatus, String routeStatus){
		return parcelRouteRepository.getAllParcelRoutofCountries(countryStatus, routeStatus);
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<ParcelRoute> findPage(String status, PageParameter pageParameter){
		Specification<ParcelRoute> specification = new Specification<ParcelRoute>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ParcelRoute> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
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
		return parcelRouteRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public ParcelRoute formToModal(Form form) {
		ParcelRoute parcelRoute = new ParcelRouteBuilder().build();
		BeanUtils.copyProperties(form, parcelRoute);
		return parcelRoute;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(ParcelRoute parcelRoute, String adminId) {
		if(!Optional.ofNullable(parcelRoute.getId()).isPresent()) {				
			this.insertSetting(parcelRoute, adminId);
		}else {
			this.updateSetting(parcelRoute, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(ParcelRoute parcelRoute) {
		parcelRouteRepository.save(parcelRoute);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(ParcelRoute parcelRoute, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new ParcelRouteBuilder(parcelRoute)
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
	private void updateSetting(ParcelRoute parcelRoute, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new ParcelRouteBuilder(parcelRoute)
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
		Optional<ParcelRoute> parcelRouteOptional = this.findTopByOrderByIdDesc();
		String id = parcelRouteOptional.isPresent() ? parcelRouteOptional.get().getId() : "0";
		return Integer.parseInt(id);
	}
	/**
	 * 取得最後一個CountryArea
	 * @return
	 */
	private Optional<ParcelRoute> findTopByOrderByIdDesc() {
		return parcelRouteRepository.findTopByOrderByIdDesc();
	}
}
