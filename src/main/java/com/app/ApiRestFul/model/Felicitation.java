package com.app.ApiRestFul.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name="felicitation")
public class Felicitation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "Type")
	private int Type;
	@Column(name = "Estate")
	private boolean Estate;
	@Column(name = "Image")
	private String image;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_kid")
	private Kid kid;

	public Felicitation() {

	}

	public Felicitation(Long id, int type, String image) {
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

	public int isType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isEstate() {
		return Estate;
	}

	public void setEstate(boolean estate) {
		Estate = estate;
	}

	@Override
	public String toString() {
		return "Felicitation [id=" + id + ", Type=" + Type + ", Estate=" + Estate + ", image=" + image + ", kid=" + kid
				+ "]";
	}
}
