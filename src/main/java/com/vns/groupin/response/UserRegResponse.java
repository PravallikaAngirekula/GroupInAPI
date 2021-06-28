package com.vns.groupin.response;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegResponse {
	
	Boolean status;
//	UserRegistration userReg;
	String otp;
	String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Long userId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String token;
	
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
//	public UserRegistration getUserReg() {
//		return userReg;
//	}
//	public void setUserReg(UserRegistration userReg) {
//		this.userReg = userReg;
//	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	

}
