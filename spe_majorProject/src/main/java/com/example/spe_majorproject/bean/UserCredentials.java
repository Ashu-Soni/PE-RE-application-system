package com.example.spe_majorproject.bean;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class UserCredentials {
	
	@Id
	private String email;
	
	private String password;

	private String userType;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType=userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCredentials{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				", userType='" + userType + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
