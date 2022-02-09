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
import com.front.chuchuPurchasingAgent.ModelBuilders.ParcelRouteNoteBuilder;
import com.front.chuchuPurchasingAgent.Models.PageParameter;
import com.front.chuchuPurchasingAgent.Models.ParcelRouteNote;
import com.front.chuchuPurchasingAgent.Repositories.ParcelRouteNoteRepository;
import com.front.chuchuPurchasingAgent.tool.util.Id;

@Service
public class ParcelRouteNoteService {
	@Autowired
	private ParcelRouteNoteRepository parcelRouteNoteRepository;
	
	/**
	 * 取單一
	 * @param parcelRoute
	 * @return
	 * @throws Exception
	 */
	public Optional<ParcelRouteNote> findOne(ParcelRouteNote parcelRouteNote) throws Exception {
		Example<ParcelRouteNote> example = Example.of(parcelRouteNote); 
		return parcelRouteNoteRepository.findOne(example);		
	}
	
	/**
	 * 依照取得分頁
	 * @param pageSetting
	 * @return
	 */
	public Page<ParcelRouteNote> findPage(String countryParcelRouteId, PageParameter pageParameter){
		Specification<ParcelRouteNote> specification = new Specification<ParcelRouteNote>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<ParcelRouteNote> root, CriteriaQuery<?> cQuery, CriteriaBuilder criteriaBuilder) {
				//條件
				List<Predicate> predicatesList = new ArrayList<>();
				Predicate[] predicates = new Predicate[predicatesList.size()];
				if(StringUtils.isNoneBlank(countryParcelRouteId)) {
					Predicate countryParcelRouteIdPredicate = criteriaBuilder.equal(root.get("countryParcelRouteId"), countryParcelRouteId);
					predicatesList.add(countryParcelRouteIdPredicate);
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
		return parcelRouteNoteRepository.findAll(specification, pageable);
	}
	
	
	/**
	 * 表單轉物件
	 * @param form
	 * @return
	 */
	public ParcelRouteNote formToModal(Form form) {
		ParcelRouteNote parcelRouteNote = new ParcelRouteNoteBuilder().build();
		BeanUtils.copyProperties(form, parcelRouteNote);
		return parcelRouteNote;
	}
	/**
	 * 選擇儲存方式
	 * @param currency
	 * @param adminId
	 */
	public void selectSaveType(ParcelRouteNote parcelRouteNote, String adminId) {
		if(!Optional.ofNullable(parcelRouteNote.getId()).isPresent()) {				
			this.insertSetting(parcelRouteNote, adminId);
		}else {
			this.updateSetting(parcelRouteNote, adminId);
		}
	}
	/**
	 * 儲存
	 * @param currency
	 */
	public void save(ParcelRouteNote parcelRouteNote) {
		parcelRouteNoteRepository.save(parcelRouteNote);
	}
	/**
	 * 刪除
	 * @param currency
	 */
	public void delete(ParcelRouteNote parcelRouteNote) {
		parcelRouteNoteRepository.delete(parcelRouteNote);
	}
	/**
	 * 寫入設定
	 * @param currency
	 * @param adminId
	 */
	private void insertSetting(ParcelRouteNote parcelRouteNote, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new ParcelRouteNoteBuilder(parcelRouteNote)
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
	private void updateSetting(ParcelRouteNote parcelRouteNote, String adminId) {
		Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
		new ParcelRouteNoteBuilder(parcelRouteNote)
		.buildModifyTime(now)
		.buildModifier(adminId);
	}
}
