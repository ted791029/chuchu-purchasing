package com.front.chuchuPurchasingAgent.Services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.transaction.annotation.Transactional;

import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.ModelBuilders.QueryBuilder;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.Query;
import com.front.chuchuPurchasingAgent.Models.QueryProduct;
import com.front.chuchuPurchasingAgent.Repositories.QueryRepository;
import com.front.chuchuPurchasingAgent.tool.util.DateUtil;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class QueryService {
	@Autowired
	QueryRepository queryRepository;
	@Autowired
	private QueryProductSerivce queryProductSerivce;
	
	/**
	 * form 轉 model
	 * @throws Exception 
	 */
	public Query formToModel(Form form) throws Exception {
		Query query = new QueryBuilder().build();
		BeanUtils.copyProperties(form, query);
		return query;
	}
	
	/**
	 * 寫入設定
	 * @return
	 * @throws Exception 
	 */
	public Query insertSetting(Query query) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		 return new QueryBuilder(query)
				 .buildId(Id.id32())
				 .buildCreateTime(now)
				 .buildCreator(query.getEmail())
				 .buildModifyTime(now)
				 .buildModifier(query.getEmail())
				 .build();
	}
	
	/**
	 * 儲存
	 * @param query
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(Query query, List<QueryProduct> products) throws Exception {
		queryRepository.save(query);
		queryProductSerivce.saveList(query, products);
	}
	/**
	 * 查單一
	 * @param query
	 */
	public Optional<Query> findOne(Query query) {
		Example<Query> example =  Example.of(query);
		return queryRepository.findOne(example);
	}
	/**
	 * 依照email取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<Query> findPageByEmail(String email, Date start, Date end, PageParameter pageParameter){
		Specification<Query> specification = new Specification<Query>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Query> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				if(StringUtils.isNoneBlank(email)) {
					Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
					predicatesList.add(emailPredicate);
				}
				if(Optional.ofNullable(start).isPresent()) {
					Predicate createTimePredicate = criteriaBuilder.greaterThan(root.get("createTime"), start);
					predicatesList.add(createTimePredicate);
				}
				if(Optional.ofNullable(end).isPresent()) {
					Predicate createTimePredicate = criteriaBuilder.lessThan(root.get("createTime"), DateUtil.getDayEnd(end));
					predicatesList.add(createTimePredicate);
				}
				Predicate[] predicates = new Predicate[predicatesList.size()];
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.desc(root.get("createTime")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return queryRepository.findAll(specification, pageable);
	}
	
	/**
	 * 依照memberId取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<Query> findPageByMemberId(String memberId, Date start, Date end, PageParameter pageParameter){
		Specification<Query> specification = new Specification<Query>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Query> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				List<Predicate> predicatesList = new ArrayList<>();
				if(StringUtils.isNoneBlank(memberId)) {
					Predicate memberIdPredicate = criteriaBuilder.equal(root.get("memberId"), memberId);
					predicatesList.add(memberIdPredicate);
				}
				if(Optional.ofNullable(start).isPresent()) {
					Predicate createTimePredicate = criteriaBuilder.greaterThan(root.get("createTime"), start);
					predicatesList.add(createTimePredicate);
				}
				if(Optional.ofNullable(end).isPresent()) {
					Predicate createTimePredicate = criteriaBuilder.lessThan(root.get("createTime"), DateUtil.getDayEnd(end));
					predicatesList.add(createTimePredicate);
				}
				Predicate[] predicates = new Predicate[predicatesList.size()];
				cQuery.where(criteriaBuilder.and(predicatesList.toArray(predicates)));
				//排序
				List<Order> orders = new ArrayList<Order>();
				orders.add(criteriaBuilder.desc(root.get("createTime")));
				cQuery.orderBy(orders);
				return cQuery.getRestriction();
			}
		};
		Pageable pageable = PageRequest.of(pageParameter.getPageNo(), pageParameter.getPageSize());
		return queryRepository.findAll(specification, pageable);
	}
}
