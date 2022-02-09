package com.front.chuchuPurchasingAgent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.LocalFee;

@Repository
public interface LocalFeeRepository extends JpaRepository<LocalFee, Long>{

}
