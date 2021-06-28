package com.vns.groupin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	State findByStateName(String stateName);

	State findByStateId(int stateId);

	@Query(value ="SELECT * FROM state WHERE country_code = :country_code", nativeQuery= true)
	List<State> findByCountry(@Param("country_code") String countryCode);
	
	

}
