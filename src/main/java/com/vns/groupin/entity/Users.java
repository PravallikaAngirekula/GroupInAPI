package com.vns.groupin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name= "username")
	private String userName;
	
	@Column(name= "password")
	private String password;
	
//	@Column(name= "serverkey")
//	private String serverKey;
//	
//	@Column(name= "salt")
//	private String salt;
	
//	@Column(name= "iterationcount")
//	private String iterationCount;
	
//	@Column(name= "created_at")
//	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getServerKey() {
//		return serverKey;
//	}
//
//	public void setServerKey(String serverKey) {
//		this.serverKey = serverKey;
//	}
//
//	public String getSalt() {
//		return salt;
//	}
//
//	public void setSalt(String salt) {
//		this.salt = salt;
//	}
//
//	public String getIterationCount() {
//		return iterationCount;
//	}
//
//	public void setIterationCount(String iterationCount) {
//		this.iterationCount = iterationCount;
//	}

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
	
	
	

}
