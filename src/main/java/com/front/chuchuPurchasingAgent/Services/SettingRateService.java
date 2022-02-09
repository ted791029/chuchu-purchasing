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
import com.front.chuchuPurchasingAgent.ModelBuilders.SettingRateBuilder;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.SettingRate;
import com.front.chuchuPurchasingAgent.Repositories.SettingRateRepository;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class SettingRateService {
	@Autowired
	private SettingRateRepository settingRateRepository;
	
	public Optional<SettingRate> findOne(SettingRate settingRate){
		Example<SettingRate> settingRateEx = Example.of(settingRate);
		return settingRateRepository.findOne(settingRateEx);
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<SettingRate> findPage(String whereId, String customerTypeId, String countryId, PageParameter pageParameter){
		Specification<SettingRate> specification = new Specification<SettingRate>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<SettingRate> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				if(StringUtils.isNoneBlank(whereId)) {
					Predicate whereIdPredicate = criteriaBuilder.equal(root.get("whereId"), whereId);
					predicatesList.add(whereIdPredicate);
				}
				if(StringUtils.isNoneBlank(customerTypeId)) {
					Predicate customerTypeIdPredicate = criteriaBuilder.equal(root.get("customerTypeId"), customerTypeId);
					predicatesList.add(customerTypeIdPredicate);
				}
				if(StringUtils.isNoneBlank(countryId)) {
					Predicate countryIdPredicate = criteriaBuilder.equal(root.get("countryId"), countryId);
					predicatesList.add(countryIdPredicate);
				}
				Predicate[] predicates = new Predicate[predicatesList.size()];
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.asc(root.get("whereId")));
				orders.add(criteriaBuilder.asc(root.get("countryId")));
				orders.add(criteriaBuilder.asc(root.get("customerTypeId")));
				orders.add(criteriaBuilder.asc(root.get("id")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return settingRateRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public SettingRate formToModal(Form form) {
		SettingRate settingRate = new SettingRateBuilder().build();
		BeanUtils.copyProperties(form, settingRate);
		return settingRate;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(SettingRate settingRate, String adminId) {
		if(!Optional.ofNullable(settingRate.getId()).isPresent()) {				
			this.insertSetting(settingRate, adminId);
		}else {
			this.updateSetting(settingRate, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(SettingRate settingRate) {
		settingRateRepository.save(settingRate);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(SettingRate settingRate, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new SettingRateBuilder(settingRate)
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
	private void updateSetting(SettingRate settingRate, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new SettingRateBuilder(settingRate)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}
}
