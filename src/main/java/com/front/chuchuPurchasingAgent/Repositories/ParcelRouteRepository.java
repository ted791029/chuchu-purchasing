package com.front.chuchuPurchasingAgent.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.front.chuchuPurchasingAgent.JoinModel.ParcelRouteJoinCountry;
import com.front.chuchuPurchasingAgent.Models.ParcelRoute;

@Repository
public interface ParcelRouteRepository extends JpaRepository<ParcelRoute, Long>, JpaSpecificationExecutor<ParcelRoute>{
	
	@Query(value=""
			+ "SELECT PR.ID, PR.NAME, C.ID AS COUNTRYID FROM PA_COUNTRY_PARCEL_ROUTE AS CPR INNER JOIN "
			+ "PA_PARCEL_ROUTE AS PR ON CPR.ROUTE_ID = PR.ID INNER JOIN "
			+ "PA_COUNTRY AS C ON CPR.COUNTRY_ID = C.ID WHERE PR.STATUS = ?1 AND C.STOREHOUSE_STATUS = ?2", nativeQuery = true)
	public List<ParcelRouteJoinCountry> getAllParcelRoutofCountries(String countryStatus, String routeStatus);
	
	public Optional<ParcelRoute> findTopByOrderByIdDesc();
}
