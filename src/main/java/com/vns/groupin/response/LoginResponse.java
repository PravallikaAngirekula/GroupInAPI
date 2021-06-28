package com.vns.groupin.response;

import com.vns.groupin.entity.UserRegistration;

public class LoginResponse {
	
	Boolean status;
	UserRegistration userReg;
	String message;
	
	String token1;
	
	

	public String getToken1() {
		return token1;
	}
	public void setToken1(String token1) {
		this.token1 = token1;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public UserRegistration getUserReg() {
		return userReg;
	}
	public void setUserReg(UserRegistration userReg) {
		this.userReg = userReg;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
