package com.app.ApiRestFul.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @author=Jesus Garcia Luque 
 */

@Entity
@Table(name = "kid")
public class Kid implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "Name")
	private String Name;
	@Column(name = "BirthDate")
	private LocalDate BirthDate;
	@Column(name = "Gender")
	private boolean Gender;
	@JsonIgnoreProperties(value = { "kid" }, allowSetters = true)
	@JoinTable(name = "client_kid", joinColumns = @JoinColumn(name = "id_kid"), inverseJoinColumns = @JoinColumn(name = "id_client"))
    @ManyToMany()
	private List<Client> client; // serian los padres
	
	@JsonIgnoreProperties(value = { "kid" }, allowSetters = true)
	@OneToMany(mappedBy = "kid", cascade = CascadeType.ALL)
	private List<Felicitation> felicitations;

	public Kid() {

	}

	public Kid(Long id, String name, LocalDate birthDate, boolean gender) {
		this.id = id;
		this.Name = name;
		this.BirthDate = birthDate;
		this.Gender = gender;
		this.client=new ArrayList<Client>();
		this.felicitations = new ArrayList<Felicitation>();
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

	public LocalDate getBirthDate() {
		return BirthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		BirthDate = birthDate;
	}

	public boolean isGender() {
		return Gender;
	}

	public void setGender(boolean gender) {
		Gender = gender;
	}

	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
	}

	public List<Felicitation> getFelicitations() {
		return felicitations;
	}

	public void setFelicitations(List<Felicitation> felicitations) {
		this.felicitations = felicitations;
	}

	@Override
	public String toString() {
		return "Kid [id=" + id + ", Name=" + Name + ", BirthDate=" + BirthDate + ", Gender=" + Gender
				+ ", felicitations=" + felicitations + "]";
	}
}

