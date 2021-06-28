package com.vns.groupin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.Country;
import com.vns.groupin.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepo;

	public List<Country> getCountriesList() {
		List<Country> countryReg = new ArrayList<>();
		countryRepo.findAll().forEach(countryReg::add);
		return countryReg;
	}

//	public List<Country> findByCountryId(Long countryId) {
//		// TODO Auto-generated method stub
//		return countryRepo.findByCou;
//	}

	
}
