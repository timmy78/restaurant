package com.exercice.tprestaurant.backend.status;

import com.exercice.tprestaurant.backend.data.User;


public class StatusUserRegister { 
	private String status; 
	private String message;
	private String code;
	private User user;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}