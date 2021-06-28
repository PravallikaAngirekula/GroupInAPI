package com.vns.groupin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.UserRegistration;


@Repository
public interface UserRegRepository extends JpaRepository<UserRegistration, Long> {

	UserRegistration findByMobileNumber(String mobileNumber);
	
	UserRegistration findMobileByMobileNumber(String mobileNumber);
	
	
	UserRegistration findUserByMobileNumber(String mobileNumber);
	
	UserRegistration findByUserId(Long userId);                                            
	
	
	@Query(value = "SELECT * FROM user_reg u, otp_log l WHERE u.mobile_number = l.mobile_number and u.mbl_num_otp = l.mbl_num_otp and u.mobile_number= :mobile_number and l.mbl_num_otp= :mbl_num_otp", nativeQuery = true)
	UserRegistration findByMobileNumber(@Param("mobile_number") String mobileNumber,
			@Param("mbl_num_otp") String mblNumOtp);

	
////	@Query(value="select concat(country_code,mobile_number) as mobile_number from user_reg where mobile_number = :mobileNumber", nativeQuery=true)
//	UserRegistration findMobileCntryByMobileNUmber(@ String mobileNumber);

	



}
