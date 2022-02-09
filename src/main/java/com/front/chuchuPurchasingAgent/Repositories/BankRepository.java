package com.front.chuchuPurchasingAgent.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.Bank;
@Repository
public interface BankRepository extends JpaRepository<Bank, Long>, JpaSpecificationExecutor<Bank>{
	
	public Optional<Bank> findTopByOrderByIdDesc();
}
