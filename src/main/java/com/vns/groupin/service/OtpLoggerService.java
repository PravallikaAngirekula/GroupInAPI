package com.vns.groupin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.groupin.entity.OtpLogger;
import com.vns.groupin.entity.UserRegistration;
import com.vns.groupin.repository.OtpLoggerRepository;
import com.vns.groupin.repository.UserGet;

@Service
public class OtpLoggerService {
	
	@Autowired
	private OtpLoggerRepository otpLogRepo;

	public OtpLogger findByMobileNumber(String mblNumOtp, String mobileNumber) {
		System.out.println(mblNumOtp );
		System.out.println(mobileNumber);
		return otpLogRepo.findByMobileNumber(mblNumOtp,mobileNumber);

	}

	public OtpLogger saveMobileNumber(OtpLogger logger) {
		return otpLogRepo.save(logger);
	}

	public OtpLogger findByPhoneNumber(String mobileNumber) {
		return otpLogRepo.findPhoneByMobileNumber(mobileNumber);
	}

//	public OtpLogger findOTPByMobileNumber(String mblNumOtp, String mobileNumber) {
//		// TODO Auto-generated method stub
//		return otpLogRepo.findOtpByMobileNumber(mblNumOtp,mobileNumber);
//	}



	

}
