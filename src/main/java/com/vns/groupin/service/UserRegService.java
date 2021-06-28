package com.vns.groupin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.repository.UserRegRepository;

@Service
public class UserRegService {

	@Autowired
	private UserRegRepository userRegRepo;

	public List<UserRegistration> getAllUsers() {
		List<UserRegistration> userReg = new ArrayList<>();
		userRegRepo.findAll().forEach(userReg::add);
		return userReg;
	}

	public UserRegistration saveUser(UserRegistration user) {
			return userRegRepo.save(user);
	}

	public UserRegistration findByMobileNumber(String mobileNumber, String mblNumOtp) {
		return userRegRepo.findByMobileNumber(mobileNumber, mblNumOtp);
	}

	public UserRegistration updateUserDetails(UserRegistration user) {
		return userRegRepo.save(user);

	}

	public UserRegistration findByMobileNumber(String mobileNumber) {
		return userRegRepo.findByMobileNumber(mobileNumber);
	}

	public UserRegistration getByUserId(Long userId) {
		return userRegRepo.findByUserId(userId);
	}

	
	public UserRegistration findPhoneNumber(String mobileNumber) {
		System.out.println("checking mbl number in service class");
		return userRegRepo.findMobileByMobileNumber(mobileNumber);
	}


	public UserRegistration findMobileByMobileNumber(String mobileNumber) {
		// TODO Auto-generated method stub
		return userRegRepo.findUserByMobileNumber(mobileNumber);
	}

//	public UserRegistration findMobileCntryCodeByMobileNumber(String mobileNumber) {
//		// TODO Auto-generated method stub
//		return userRegRepo.findMobileCntryByMobileNUmber(mobileNumber);
//	}

	
	

}
