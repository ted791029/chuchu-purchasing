package com.front.chuchuPurchasingAgent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.PurchasingRate;
@Repository
public interface PurchasingRateRepository extends JpaRepository<PurchasingRate, Long>{

}
	