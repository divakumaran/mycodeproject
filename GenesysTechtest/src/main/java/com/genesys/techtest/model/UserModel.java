package com.genesys.techtest.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class UserModel {
	
	@NotNull
	private String userName;
	@NotNull
	@Email
	private String userEmail; 
	@NotNull
	private String userPass;
	
	private Date lastLoginDate;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate =lastLoginDate;
	}
	
	
}
