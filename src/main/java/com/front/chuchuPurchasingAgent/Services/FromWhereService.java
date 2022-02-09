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

import com.front.chuchuPurchasingAgent.Models.FromWhere;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.FromWhereRepository;

@Service
public class FromWhereService {
	@Autowired
	private FromWhereRepository fromWhereRepository;
	
	/**
	 * 依照取得分頁
	 * @param status
	 * @param pageParameter
	 * @return
	 */
	public Page<FromWhere> findPage(String status, PageParameter pageParameter){
		Specification<FromWhere> specification = new Specification<FromWhere>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<FromWhere> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
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
		return fromWhereRepository.findAll(specification, pageable);
	}
	/**
	 * 取得列表
	 * @param fromWhere
	 * @return
	 */
	public List<FromWhere> findAll(FromWhere fromWhere){
		Example<FromWhere> example = Example.of(fromWhere); 
		return fromWhereRepository.findAll(example);
	}
	
	/**
	 * 取得ID對照Map
	 * @param countryAreas
	 * @return
	 */
	public Map<String, String> getFromWhereIdMap(List<FromWhere> fromWheres){
		Map<String, String> map = new HashMap<>();
		for(FromWhere fromWhere : fromWheres) {
			map.put(fromWhere.getId(), fromWhere.getName());
		}
		return map;
	}
}
