package com.exercice.tprestaurant.backend.data;

import java.util.List;

public class Commercant { 
	private String id; 
	private String commercantTypeId; 
	private String commercantTypeNom; 
	private String presentationTitre; 
	private String presentationContenu; 
	private String bonplanTitre; 
	private String bonplanContenu; 
	private String bonplanPrestationId;
	private String enseigne; 
	private String tel; 
	private String email; 
	private String facebook; 
	private String twitter; 
	private String notificationAnniversaire; 
	private String fileLogo; 
	private String isActive; 
	private List<Photos> photos; 
	private String messageFermeture;
	private String latitude;
	private String longitude;
	private int distance;
	private String notificationGeolocalisation;
	private long dateGeolocalisation;

	public String getId() { 
		return id; 
	} 

	public String getCommercantTypeId() { 
		return commercantTypeId; 
	} 

	public String getCommercantTypeNom() { 
		return commercantTypeNom; 
	} 

	public String getPresentationTitre() { 
		return presentationTitre; 
	} 

	public String getPresentationContenu() { 
		return presentationContenu; 
	} 

	public String getBonplanTitre() { 
		return bonplanTitre; 
	} 

	public String getBonplanContenu() { 
		return bonplanContenu; 
	} 

	public String getEnseigne() { 
		return enseigne; 
	} 

	public String getTel() { 
		return tel; 
	} 

	public String getEmail() { 
		return email; 
	} 

	public String getFacebook() { 
		return facebook; 
	} 

	public String getTwitter() { 
		return twitter; 
	} 

	public String getNotificationAnniversaire() { 
		return notificationAnniversaire; 
	} 

	public String getFileLogo() { 
		return fileLogo; 
	} 

	public String getIsActive() { 
		return isActive; 
	} 

	public List<Photos> getPhotos() { 
		return photos; 
	} 

	public void setId(String id) { 
		this.id = id; 
	} 

	public void setCommercantTypeId(String commercantTypeId) { 
		this.commercantTypeId = commercantTypeId; 
	} 

	public void setCommercantTypeNom(String commercantTypeNom) { 
		this.commercantTypeNom = commercantTypeNom; 
	} 

	public void setPresentationTitre(String presentationTitre) { 
		this.presentationTitre = presentationTitre; 
	} 

	public void setPresentationContenu(String presentationContenu) { 
		this.presentationContenu = presentationContenu; 
	} 

	public void setBonplanTitre(String bonplanTitre) { 
		this.bonplanTitre = bonplanTitre; 
	} 

	public void setBonplanContenu(String bonplanContenu) { 
		this.bonplanContenu = bonplanContenu; 
	} 

	public void setEnseigne(String enseigne) { 
		this.enseigne = enseigne; 
	} 

	public void setTel(String tel) { 
		this.tel = tel; 
	} 

	public void setEmail(String email) { 
		this.email = email; 
	} 

	public void setFacebook(String facebook) { 
		this.facebook = facebook; 
	} 

	public void setTwitter(String twitter) { 
		this.twitter = twitter; 
	} 

	public void setNotificationAnniversaire(String notificationAnniversaire) { 
		this.notificationAnniversaire = notificationAnniversaire; 
	} 

	public void setFileLogo(String fileLogo) { 
		this.fileLogo = fileLogo; 
	} 

	public void setIsActive(String isActive) { 
		this.isActive = isActive; 
	} 

	public void setPhotos(List<Photos> photos) { 
		this.photos = photos; 
	}

	public String getBonplanPrestationId() {
		return bonplanPrestationId;
	}

	public void setBonplanPrestationId(String bonplanPrestationId) {
		this.bonplanPrestationId = bonplanPrestationId;
	}

	public String getMessageFermeture() {
		return messageFermeture;
	}

	public void setMessageFermeture(String messageFermeture) {
		this.messageFermeture = messageFermeture;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getNotificationGeolocalisation() {
		return notificationGeolocalisation;
	}

	public void setNotificationGeolocalisation(String notificationGeolocalisation) {
		this.notificationGeolocalisation = notificationGeolocalisation;
	}

	public long getDateGeolocalisation() {
		return dateGeolocalisation;
	}

	public void setDateGeolocalisation(long dateGeolocalisation) {
		this.dateGeolocalisation = dateGeolocalisation;
	}
	
	
	
	

}