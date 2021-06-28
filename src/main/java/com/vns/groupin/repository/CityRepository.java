package com.vns.groupin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.City;
import com.vns.groupin.entity.State;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

//	@Query(value = "SELECT * FROM city  WHERE s_id = :stateId", nativeQuery = true)
//	List<City> findCityByStateSId(int stateSId);

	@Query(value ="SELECT * FROM city WHERE state_id = :state_id", nativeQuery= true)
	List<City> findByStateId(@Param("state_id") int stateId);




	

}
