package com.exercice.tprestaurant.backend.data;


public class User { 
	private int id;
	private String civilite; 
	private String nom; 
	private String prenom; 
	private String Email; 
	private String Password; 
	private String mobile; 
	private String tel; 
	private String date_anniversaire;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDate_anniversaire() {
		return date_anniversaire;
	}

	public void setDate_anniversaire(String date_anniversaire) {
		this.date_anniversaire = date_anniversaire;
	}


	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}
	
	
	
	
}