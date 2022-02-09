package com.front.chuchuPurchasingAgent.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.Models.QueryProduct;

@Repository
public interface QueryProductRepository extends JpaRepository<QueryProduct, Long>{

}
