package com.app.ApiRestFul.model;

import java.io.Serializable;

public class Content implements Serializable{
	String message;
	String urlImage;
	Client client;
	Felicitation felicitation;
	
	public Content() {
		
	}

	public Content(String message, String urlImage,Client c,Felicitation felicitation) {
		this.message = message;
		this.urlImage = urlImage;
		this.client=c;
		this.felicitation=felicitation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client c) {
		this.client = c;
	}
	
	

	public Felicitation getFelicitation() {
		return felicitation;
	}

	public void setFelicitation(Felicitation felicitation) {
		this.felicitation = felicitation;
	}

	@Override
	public String toString() {
		return "Content [message=" + message + ", urlImage=" + urlImage + ", telefono=" + "]";
	}
}
