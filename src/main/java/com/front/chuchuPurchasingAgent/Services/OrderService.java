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

import com.front.chuchuPurchasingAgent.Constants;
import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.ModelBuilders.MyOrderBuilder;
import com.front.chuchuPurchasingAgent.Models.MyOrder;
import com.front.chuchuPurchasingAgent.Models.OrderProduct;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.MyOrderRepository;
import com.front.chuchuPurchasingAgent.tool.util.DateUtil;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class OrderService {
	@Autowired
	private MyOrderRepository myOrderRepository;
	@Autowired
	private OrderProductService orderProductService;
	/**
	 * form 轉 model
	 * @throws Exception 
	 */
	public MyOrder formToModel(Form form) throws Exception {
		MyOrder MyOrder = new MyOrderBuilder().build();
		BeanUtils.copyProperties(form, MyOrder);
		return MyOrder;
	}
	
	/**
	 * 寫入設定
	 * @return
	 * @throws Exception 
	 */
	public MyOrder insertSetting(String memberId, MyOrder myOrder) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		 return new MyOrderBuilder(myOrder)
				 .buildId(Id.id32())
				 .buildMemberId(memberId)
				 .buildCreateTime(now)
				 .buildCreator(memberId)
				 .buildModifyTime(now)
				 .buildModifier(memberId)
				 .buildStatusId(Constants.ORDER_STATUS_CREATED)
				 .build();
	}
	
	/**
	 * 儲存
	 * @param query
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(String memberId, MyOrder myOrder, List<OrderProduct> products) throws Exception {
		this.insertSetting(memberId, myOrder);
		myOrderRepository.save(myOrder);
		orderProductService.saveList(memberId, myOrder, products);
	}
	/**
	 * 查單一
	 * @param order
	 * @return
	 */
	public Optional<MyOrder> findOne(MyOrder myOrder) {
		Example<MyOrder> orderEx = Example.of(myOrder);
		return myOrderRepository.findOne(orderEx);
	}
	
	/**
	 * 依照memberId取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<MyOrder> findPageByMemberId(String memberId, Date start, Date end, PageParameter pageParameter){
		Specification<MyOrder> specification = new Specification<MyOrder>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<MyOrder> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
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
		return myOrderRepository.findAll(specification, pageable);
	}
}
