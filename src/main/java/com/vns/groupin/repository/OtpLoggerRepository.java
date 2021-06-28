package com.vns.groupin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vns.groupin.entity.OtpLogger;
import com.vns.groupin.entity.UserRegistration;

@Repository
public interface OtpLoggerRepository extends JpaRepository<OtpLogger, Long> {

	@Query(value = "SELECT * FROM otp_log  WHERE mbl_num_otp= ?1 and mobile_number= ?2 ", nativeQuery = true)
	OtpLogger findByMobileNumber(String mblNumOtp, String mobileNumber);
	
//	
//	@Query(value = "SELECT * FROM otp_log  WHERE mbl_num_otp= :mbl_num_otp and mobile_number= :mobile_number ", nativeQuery = true)
//	OtpLogger findByMobileNumber(@Param("mbl_num_otp") String mblNumOtp, @Param("mobile_number") String mobileNumber);

	

	OtpLogger findPhoneByMobileNumber(String mobileNumber);


//	@Query(value = "SELECT * FROM otp_log  WHERE mbl_num_otp= ?1 and mobile_number= ?2 ", nativeQuery = true)
//	OtpLogger findOtpByMobileNumber(String mblNumOtp, String mobileNumber);

	


}
