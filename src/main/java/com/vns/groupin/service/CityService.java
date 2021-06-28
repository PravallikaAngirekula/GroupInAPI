package com.vns.groupin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.City;
import com.vns.groupin.entity.State;
import com.vns.groupin.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;

//	public List<City> getCityByState(int stateSId) {
//	
//		return cityRepo.findCityByStateSId(stateSId);
//	}

	public List<City> getCitiesList() {
		List<City> cityReg = new ArrayList<>();
		cityRepo.findAll().forEach(cityReg::add);
		return cityReg;
		
	}

	public List<City> findByStateId(int stateId) {
		// TODO Auto-generated method stub return stateRepo.findByCountry(countryId);
		return cityRepo.findByStateId(stateId);
	}
}
