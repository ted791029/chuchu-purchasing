package com.front.chuchuPurchasingAgent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.CurrencyRate;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long>, JpaSpecificationExecutor<CurrencyRate>{
}
