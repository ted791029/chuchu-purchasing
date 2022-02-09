package com.front.chuchuPurchasingAgent.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.CountryArea;

@Repository
public interface CountryAreaRepository extends JpaRepository<CountryArea, Long>, JpaSpecificationExecutor<CountryArea>{
	
	public Optional<CountryArea> findTopByOrderByIdDesc();
}
