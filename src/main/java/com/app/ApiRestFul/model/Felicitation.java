package com.app.ApiRestFul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mysql.cj.jdbc.Blob;

public class Felicitation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "Type")
	private boolean Type;
	@Column(name = "Image")
	private Blob image;

	public Felicitation() {

	}

	public Felicitation(Long id, boolean type, Blob image) {
		this.id = id;
		this.Type = type;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isType() {
		return Type;
	}

	public void setType(boolean type) {
		Type = type;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

}
