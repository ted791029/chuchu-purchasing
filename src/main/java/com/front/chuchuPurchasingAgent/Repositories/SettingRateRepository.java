package com.front.chuchuPurchasingAgent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.SettingRate;

@Repository
public interface SettingRateRepository extends JpaRepository<SettingRate, Long>, JpaSpecificationExecutor<SettingRate>{

}
