package com.vns.groupin.response;


/**
 * @author ADMIN
 *
 */
public class OtpLoggerResponse {

	Boolean status;
	String message;
	String token;
	Boolean profileUpdation;
	boolean userExists;
	
	String JID;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public boolean isUserExists() {
		return userExists;
	}
	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
	public boolean isProfileUpdation() {
		return profileUpdation;
	}
	public void setProfileUpdation(boolean profileUpdation) {
		this.profileUpdation = profileUpdation;
	}
	public Boolean getProfileUpdation() {
		return profileUpdation;
	}
	public void setProfileUpdation(Boolean profileUpdation) {
		this.profileUpdation = profileUpdation;
	}
	public String getJID() {
		return JID;
	}
	public void setJID(String jID) {
		JID = jID;
	}

	
	
	
	
	
	
	
}
