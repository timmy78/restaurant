package com.exercice.tprestaurant.backend.data;
public class Photos { 
	private String id; 
	private String fileImg; 
	private String nom; 
	//private String url; 
	//private String position; 
	private String commentaire; 

	public String getId() { 
		return id; 
	} 

	public String getFileImg() { 
		return fileImg; 
	} 

	public String getNom() { 
		return nom; 
	} 
/*
	public String getUrl() { 
		return url; 
	} 

	public String getPosition() { 
		return position; 
	} 
*/
	public String getCommentaire() { 
		return commentaire; 
	} 

	public void setId(String id) { 
		this.id = id; 
	} 

	public void setFileImg(String fileImg) { 
		this.fileImg = fileImg; 
	} 

	public void setNom(String nom) { 
		this.nom = nom; 
	} 
/*
	public void setUrl(String url) { 
		this.url = url; 
	} 

	public void setPosition(String position) { 
		this.position = position; 
	} 
*/
	public void setCommentaire(String commentaire) { 
		this.commentaire = commentaire; 
	} 
}