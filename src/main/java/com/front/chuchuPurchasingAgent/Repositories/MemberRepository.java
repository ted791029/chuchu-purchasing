package com.front.chuchuPurchasingAgent.Repositories;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member>{
	
	@Transactional
	@Modifying
	@Query("update Member set email = ?1, name = ?2, phone = ?3, address = ?4, idntity_card = ?5, store_no = ?6, modify_time = ?7, modifier = ?8 where id = ?9")
	public void updateInfo(String email, String name, String phone, String address, String idntityCard, String storeNO, Timestamp modifyTime, String modifier, String id);
}
