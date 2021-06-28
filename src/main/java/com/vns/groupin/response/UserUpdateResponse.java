package com.vns.groupin.response;

import com.vns.groupin.entity.UserRegistration;

public class UserUpdateResponse {

	Boolean status;
	UserRegistration userReg;
	String message;
	
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
