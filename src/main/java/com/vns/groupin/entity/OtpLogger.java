package com.vns.groupin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp_log")
public class OtpLogger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="log_id")
	private Long logId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name = "mbl_num_otp")
	private String mblNumOtp;
	
	@Column(name ="country_code")
	private String countryCode;
	
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMblNumOtp() {
		return mblNumOtp;
	}
	public void setMblNumOtp(String mblNumOtp) {
		this.mblNumOtp = mblNumOtp;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	

}
