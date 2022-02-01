package com.app.ApiRestFul.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "kid")
public class Kid implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "Name")
	private String Name;
	@Column(name = "BirthDate")
	private Date BirthDate;
	@Column(name = "Gender")
	private boolean Gender;
	//@JsonIgnoreProperties(value = {"kid"}, allowSetters = true)
	@JoinTable(name = "client_kid", joinColumns = @JoinColumn(name="id_kid"), inverseJoinColumns = @JoinColumn(name="id_client"))
	@ManyToMany(cascade = {CascadeType.MERGE})
	private List<Client> client; //serian los padres
	@OneToMany(mappedBy = "kid", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Felicitation> felicitations;

	public Kid() {

	}

	public Kid(Long id, String name, Date birthDate, boolean gender) {
		this.id = id;
		this.Name = name;
		this.BirthDate = birthDate;
		this.Gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}

	public boolean isGender() {
		return Gender;
	}

	public void setGender(boolean gender) {
		Gender = gender;
	}

	@Override
	public String toString() {
		return "Kid [id=" + id + ", Name=" + Name + ", BirthDate=" + BirthDate + ", Gender=" + Gender  + ", felicitations=" + felicitations + "]";
	}
}
