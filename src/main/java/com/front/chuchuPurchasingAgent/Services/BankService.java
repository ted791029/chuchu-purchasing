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
import com.front.chuchuPurchasingAgent.ModelBuilders.BankBuilder;
import com.front.chuchuPurchasingAgent.Models.Bank;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.BankRepository;

@Service
public class BankService {
	@Autowired
	private BankRepository bankRepository;
	/**
	 * 查單一
	 * @param bank
	 * @return
	 */
	public Optional<Bank> findOne(Bank bank){
		Example<Bank> example = Example.of(bank); 
		return bankRepository.findOne(example);
	}
	/**
	 * 查全部
	 * @return
	 * @throws Exception
	 */
	public List<Bank> findAll(Bank bank) throws Exception {
		Example<Bank> example = Example.of(bank); 
		return bankRepository.findAll(example);		
	}
	
	/**
	 * 取得ID對照Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getBankIdMap(List<Bank> banks){
		Map<String, String> map = new HashMap<>();
		for(Bank bank : banks) {
			map.put(bank.getId(), bank.getName());
		}
		return map;
	}
	
	/**
	 * 取得ID對照帳號Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getBankAccountMap(List<Bank> banks){
		Map<String, String> map = new HashMap<>();
		for(Bank bank : banks) {
			map.put(bank.getId(), bank.getAccount());
		}
		return map;
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<Bank> findPage(String status, PageParameter pageParameter){
		Specification<Bank> specification = new Specification<Bank>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Bank> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
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
		return bankRepository.findAll(specification, pageable);
	}
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public Bank formToModal(Form form) {
		Bank bank = new BankBuilder().build();
		BeanUtils.copyProperties(form, bank);
		return bank;
	}
	
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(Bank bank, String adminId) {
		if(!Optional.ofNullable(bank.getId()).isPresent()) {				
			this.insertSetting(bank, adminId);
		}else {
			this.updateSetting(bank, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(Bank bank) {
		bankRepository.save(bank);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(Bank bank, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new BankBuilder(bank)
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
	private void updateSetting(Bank bank, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new BankBuilder(bank)
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
		Optional<Bank> countryAreaOptional = this.findTopByOrderByIdDesc();
		String id = countryAreaOptional.isPresent() ? countryAreaOptional.get().getId() : "0";
		return Integer.parseInt(id);
	}
	/**
	 * 取得最後一個CountryArea
	 * @return
	 */
	private Optional<Bank> findTopByOrderByIdDesc() {
		return bankRepository.findTopByOrderByIdDesc();
	}
}
