package com.vns.groupin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.Users;
import com.vns.groupin.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository userRepo;

	public Users saveMobileNumber(Users usr) {
		
		return userRepo.save(usr) ;
	}

	public Users findByUserName(String mobileNumber) {
		return userRepo.findByUserName(mobileNumber);
	}
	


//	public Users finduIdByUserName(String userName) {
//		// TODO Auto-generated method stub
//		return userRepo.finduIdByUserName(userName);
//	}

}
