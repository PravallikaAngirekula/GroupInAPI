package com.vns.groupin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;


@Entity
@Table(name = "user_reg")
public class UserRegistration{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_type")
	private String userType;
	
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="mbl_num_otp")
	private String mblNumOtp;
	
	@Column(name="profile_name")
	private String profileName;
	
	@Column(name="description")
	private String description;
	
	
	@Column(name="city_id")
	private String cityId;
	
	@Column(name="state_id")
	private String stateId;	
	
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="category_id")
	private String categoryId; 
	
	
	@Column(name="mobile_verified")
	private boolean mobileVerified;
	
	@Column(name="email_verified")
	private boolean emailVerified;
	
	@Column(name="is_active")
	private boolean isActive;
	

//	@Column(name = "registration_status")
//	private boolean registrartionStatus;
	

	@Column(name = "profile_completed")
	private Boolean profileUpdate = false;
	
	public Boolean getProfileUpdate() {
		return profileUpdate;
	}
	public void setProfileUpdate(Boolean profileUpdate) {
		this.profileUpdate = profileUpdate;
	}
	
//	@Column(name= "profile_completed")
//	private boolean profileCompleted = false;
	
	
//	public boolean isProfileCompleted() {
//		return profileCompleted;
//	}
//	public void setProfileCompleted(boolean profileCompleted) {
//		this.profileCompleted = profileCompleted;
//	}
//	
	

	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	public String getMblNumOtp() {
		return mblNumOtp;
	}
	public void setMblNumOtp(String mblNumOtp) {
		this.mblNumOtp = mblNumOtp;
	}
	
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCityId() {
		return cityId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public boolean isMobileVerified() {
		return mobileVerified;
	}
	public void setMobileVerified(boolean mobileVerified) {
		this.mobileVerified = mobileVerified;
	}
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	

}
