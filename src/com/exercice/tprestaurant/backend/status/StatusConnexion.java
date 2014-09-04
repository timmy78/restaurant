package com.exercice.tprestaurant.backend.status;

import com.exercice.tprestaurant.backend.data.Commercant;

public class StatusConnexion {
	private String status;
	private Commercant commercant;
	private String code;
	private String message;

	public String getStatus() { 
		return status; 
	} 

	public Commercant getCommercant() { 
		return commercant; 
	} 

	public void setStatus(String status) { 
		this.status = status; 
	} 

	public void setCommercant(Commercant commercant) { 
		this.commercant = commercant; 
	} 

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}