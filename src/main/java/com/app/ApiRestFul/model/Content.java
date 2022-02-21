package com.app.ApiRestFul.model;

public class Content {
	String message;
	String urlImage;
	Client client;
	
	public Content() {
		
	}

	public Content(String message, String urlImage,Client c) {
		this.message = message;
		this.urlImage = urlImage;
		this.client=c;
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
	
	

	public Client getC() {
		return client;
	}

	public void setC(Client c) {
		this.client = c;
	}

	@Override
	public String toString() {
		return "Content [message=" + message + ", urlImage=" + urlImage + ", telefono=" + "]";
	}
}
