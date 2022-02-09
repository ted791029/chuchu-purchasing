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

import com.front.chuchuPurchasingAgent.Controllers.Form;
import com.front.chuchuPurchasingAgent.ModelBuilders.MemberBuilder;
import com.front.chuchuPurchasingAgent.Models.Member;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Repositories.MemberRepository;
import com.front.chuchuPurchasingAgent.tool.util.DataEncrypt;
import com.front.chuchuPurchasingAgent.tool.util.DateUtil;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * form 轉 model
	 * @throws Exception 
	 */
	public Member formToModel(Form form) throws Exception {
		Member member = new MemberBuilder().build();
		BeanUtils.copyProperties(form, member);
		if(Optional.ofNullable(member.getPassword()).isPresent()) member = new  MemberBuilder(member)
				 .buildPassword(DataEncrypt.md5(member.getPassword())) //密碼加密
				 .build();
		return member;
	}
	/**
	 * 註冊設定
	 * @return
	 * @throws Exception 
	 */
	public Member registerSetting(Member member) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		 return new MemberBuilder(member)
				 .buildId(Id.id32())
				 .buildEmail(member.getAccount())//設定Email
				 .buildCreateTime(now)
				 .buildCreator(member.getId())
				 .buildModifyTime(now)
				 .buildModifier(member.getId())
				 .build();
	}
	/**
	 * 更新設定
	 * @return
	 * @throws Exception 
	 */
	public Member updateSetting(Member member) throws Exception {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		 return new MemberBuilder(member)
				 .buildModifyTime(now)
				 .buildModifier(member.getId())
				 .build();
	}
	
	/**
	 * 儲存
	 * @param member
	 */
	public void save(Member member) {
		memberRepository.save(member);
	}
	/**
	 * 取得單一
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public Optional<Member> findOne(Member member) throws Exception {
		Example<Member> example = Example.of(member); 
		return memberRepository.findOne(example);		
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<Member> findPage(String account, Date start, Date end, PageParameter pageParameter){
		Specification<Member> specification = new Specification<Member>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				List<Predicate> predicatesList = new ArrayList<>();
				if(StringUtils.isNoneBlank(account)) {
					Predicate emailPredicate = criteriaBuilder.like(root.get("account"), "%" + account + "%");
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
		return memberRepository.findAll(specification, pageable);
	}
	/**
	 * 更新會員資訊
	 * @param member
	 */
	public void updateInfo(Member member) {
		memberRepository.updateInfo(
				member.getEmail(),
				member.getName(),
				member.getPhone(),
				member.getAddress(),
				member.getIdntityCard(),
				member.getStoreNO(),
				member.getModifyTime(), 
				member.getModifier(),
				member.getId());
	}
	
}
