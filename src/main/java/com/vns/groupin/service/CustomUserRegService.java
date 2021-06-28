package com.vns.groupin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.repository.UserRegRepository;

@Service
public class CustomUserRegService implements UserDetailsService{
	@Autowired
	private UserRegRepository userRegRepo;
	
	@Override
	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		UserRegistration user= userRegRepo.findByMobileNumber(mobileNumber);
		return new org.springframework.security.core.userdetails.User(user.getMobileNumber(), user.getMblNumOtp(), new ArrayList<>());
//		return new org.springframework.security.core.userdetails.User(user., null)
	}

	public UserRegistration saveUserDetails(UserRegistration user) {
		return userRegRepo.save(user);
	}

}
