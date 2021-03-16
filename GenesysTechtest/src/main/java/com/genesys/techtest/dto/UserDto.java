/**
 * 
 */
package com.genesys.techtest.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author diva
 *
 */
@Entity
@Table(name = "USER_DATA") 
public class UserDto {
	
	@Id
	@Column (name ="USER_ID")
	private String userId;
	@Column (name ="USER_NAME" , nullable = false)
	private String userName;
	@Column (name ="USER_EMAIL" , nullable = false)
	@Email
	@NotBlank
	private String userEmail; 
	@Column (name ="USER_PASS" , nullable = false)
	private String userPass;
	@Column (name ="LAST_LOGIN_DATA")
	private Timestamp lastLoginDate;
	@Column (name ="CREATED_BY")
	private String createdBy;
	@Column (name ="CREATED_AT")
	private Timestamp createdAt;
	@Column (name ="UPDATED_BY")
	private String updatedBy;
	@Column (name ="UPDATED_AT")
	private Timestamp updatedAt;
	
	public UserDto() {
    }
	
	public UserDto(String userId, String userName, String userEmail, String userPass, Timestamp lastLoginDate,
			String createdBy, Timestamp createdAt, String updatedBy, Timestamp updatedAt) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPass = userPass;
		this.lastLoginDate = lastLoginDate;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
