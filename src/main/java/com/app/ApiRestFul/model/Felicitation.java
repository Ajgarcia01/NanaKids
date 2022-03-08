package com.app.ApiRestFul.model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @author= Gonzalo Bretones-Mora Quero 
 */
@Entity
@Table(name = "felicitation")
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
	@Column(name = "dateSend")
	private LocalDate date_send;
	@Column(name = "Image")
	private String image;
	//CONVERTIR A LOCALDATE
	@JsonIgnoreProperties(value = {"felicitations"}, allowSetters = true)
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_kid")
	private Kid kid;

	
	public Felicitation() {
		super();
	}

	public Felicitation(Long id, int type, LocalDate date, String image,Kid kid) {
		this.id = id;
		this.Type = type;
		this.date_send=date;
		this.image = image;
		this.kid=kid;
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
	
	public LocalDate getDateSend() {
		return date_send;
	}

	public void setDateSend(LocalDate dateSend) {
		this.date_send = dateSend;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}

	public int getType() {
		return Type;
	}

	@Override
	public String toString() {
		return "Felicitation [id=" + id + ", Type=" + Type + ", Estate=" + Estate + ", dateSend=" + date_send
				+ ", image=" + image + ", kid=" + kid + "]";
	}

	
}

