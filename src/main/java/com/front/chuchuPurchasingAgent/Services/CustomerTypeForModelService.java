package com.front.chuchuPurchasingAgent.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.front.chuchuPurchasingAgent.Models.CustomerType;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.CustomerTypeRepository;

@Service
public class CustomerTypeForModelService {
	@Autowired
	private CustomerTypeRepository customerTypeRepository;
	
	/**
	 * 依照取得分頁
	 * @param status
	 * @param pageParameter
	 * @return
	 */
	public Page<CustomerType> findPage(String status, PageParameter pageParameter){
		Specification<CustomerType> specification = new Specification<CustomerType>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<CustomerType> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
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
		return customerTypeRepository.findAll(specification, pageable);
	}
	/**
	 * 取得列表
	 * @param customerType
	 * @return
	 */
	public List<CustomerType> findAll(CustomerType customerType){
		Example<CustomerType> example = Example.of(customerType); 
		return customerTypeRepository.findAll(example);
	}
	/**
	 * 取得ID對照Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getCustomerTypeIdMap(List<CustomerType> customerTypes){
		Map<String, String> map = new HashMap<>();
		for(CustomerType customerType : customerTypes) {
			map.put(customerType.getId(), customerType.getName());
		}
		return map;
	}
}
