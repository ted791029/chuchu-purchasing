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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.ModelBuilders.CurrencyRateBuilder;
import com.front.chuchuPurchasingAgent.Models.CurrencyRate;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.CurrencyRateRepository;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class CurrencyRateService {
	@Autowired
	private CurrencyRateRepository currencyRateRepository;
	
	public Optional<CurrencyRate> findOne(CurrencyRate currencyRate) {
		Example<CurrencyRate> currencyRateEx = Example.of(currencyRate);
		return currencyRateRepository.findOne(currencyRateEx);
	};
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<CurrencyRate> findPage(PageParameter pageParameter){
		Specification<CurrencyRate> specification = new Specification<CurrencyRate>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<CurrencyRate> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				Predicate[] predicates = new Predicate[predicatesList.size()];
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.asc(root.get("currencyId")));
				orders.add(criteriaBuilder.asc(root.get("id")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return currencyRateRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public CurrencyRate formToModal(Form form) {
		CurrencyRate currencyRate = new CurrencyRateBuilder().build();
		BeanUtils.copyProperties(form, currencyRate);
		return currencyRate;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(CurrencyRate currencyRate, String adminId) {
		if(!Optional.ofNullable(currencyRate.getId()).isPresent()) {				
			this.insertSetting(currencyRate, adminId);
		}else {
			this.updateSetting(currencyRate, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(CurrencyRate currencyRate) {
		currencyRateRepository.save(currencyRate);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(CurrencyRate currencyRate, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CurrencyRateBuilder(currencyRate)
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
	private void updateSetting(CurrencyRate currencyRate, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new CurrencyRateBuilder(currencyRate)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}
}
