package com.vns.groupin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.City;
import com.vns.groupin.entity.State;
import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.repository.CityRepository;
import com.vns.groupin.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepo;
	private CityRepository cityRepo;

	public State findByStateName(String stateName) {
		return stateRepo.findByStateName(stateName);
	}

	public State saveState(State state) {
		return stateRepo.save(state);
	}

	public List<State> getStatesList() {
		List<State> stateReg = new ArrayList<>();
		stateRepo.findAll().forEach(stateReg::add);
		return stateReg;
	
	}

	public State getByStateId(int stateId) {
	
		return stateRepo.findByStateId(stateId);
	}

	public State findByStateId(int stateId) {
		
		return stateRepo.findByStateId(stateId);
		}

//	public List<State> findByCountryId(Long countryId) {
//		// TODO Auto-generated method stub
//		return stateRepo.findByCountry(countryId);
//	}

	public List<State> findByCountryCode(String countryCode) {
		// TODO Auto-generated method stub
		return stateRepo.findByCountry(countryCode);
	}

//	public List<City> getCityByStateId(City city) {
//		// TODO Auto-generated method stub
//		return cityRepo.findCityByStateId(city.getStateId());
//	}

//	public List<City> getCityByState(State state) {
//		State result = cityRepo.findByStateId(state.getStateId());
//		return result.getCities();
//	}
}
