package com.front.chuchuPurchasingAgent.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
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
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteOfCountryBuilder;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRouteOfCountry;
import com.front.chuchuPurchasingAgent.Repositories.ParcelRouteOfCountryRepository;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class ParcelRouteOfCountryService {
	@Autowired
	private ParcelRouteOfCountryRepository parcelRouteOfCountryRepository;
	/**
	 * 取單一
	 * @param parcelRoute
	 * @return
	 * @throws Exception
	 */
	public Optional<ParcelRouteOfCountry> findOne(ParcelRouteOfCountry parcleRouteOfCountry) throws Exception {
		Example<ParcelRouteOfCountry> example = Example.of(parcleRouteOfCountry); 
		return parcelRouteOfCountryRepository.findOne(example);		
	}
	/**
	 * 依照取得分頁
	 * @param countryId
	 * @param routeId
	 * @param pageParameter
	 * @return
	 */
	public Page<ParcelRouteOfCountry> findPage(String countryId, String routeId, PageParameter pageParameter){
		Specification<ParcelRouteOfCountry> specification = new Specification<ParcelRouteOfCountry>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ParcelRouteOfCountry> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				Predicate[] predicates = new Predicate[predicatesList.size()];
				if(StringUtils.isNoneBlank(countryId)) {
					Predicate countryIdPredicate = criteriaBuilder.equal(root.get("countryId"), countryId);
					predicatesList.add(countryIdPredicate);
				}
				if(StringUtils.isNoneBlank(routeId)) {
					Predicate routeIdPredicate = criteriaBuilder.equal(root.get("routeId"), routeId);
					predicatesList.add(routeIdPredicate);
				}
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.asc(root.get("countryId")));
				orders.add(criteriaBuilder.asc(root.get("routeId")));
				orders.add(criteriaBuilder.asc(root.get("id")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return parcelRouteOfCountryRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public ParcelRouteOfCountry formToModal(Form form) {
		ParcelRouteOfCountry parcelRouteOfCountry = new ParcelRouteOfCountryBuilder().build();
		BeanUtils.copyProperties(form, parcelRouteOfCountry);
		return parcelRouteOfCountry;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(ParcelRouteOfCountry parcelRouteOfCountry, String adminId) {
		if(!Optional.ofNullable(parcelRouteOfCountry.getId()).isPresent()) {				
			this.insertSetting(parcelRouteOfCountry, adminId);
		}else {
			this.updateSetting(parcelRouteOfCountry, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(ParcelRouteOfCountry parcelRouteOfCountry) {
		parcelRouteOfCountryRepository.save(parcelRouteOfCountry);
	}
	/**
	 * 刪除
	 * @param currency
	 */
	public void delete(ParcelRouteOfCountry parcelRouteOfCountry) {
		parcelRouteOfCountryRepository.delete(parcelRouteOfCountry);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(ParcelRouteOfCountry parcelRouteOfCountry, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new ParcelRouteOfCountryBuilder(parcelRouteOfCountry)
		.buildId(Id.id32())
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
	private void updateSetting(ParcelRouteOfCountry parcelRouteOfCountry, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new ParcelRouteOfCountryBuilder(parcelRouteOfCountry)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}

}
