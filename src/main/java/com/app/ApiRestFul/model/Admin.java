package com.app.ApiRestFul.model;

import java.io.Serial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;






@Entity
@Table(name = "admin")
public class Admin implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "_user")
	private String user;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	
	//@JsonIgnoreProperties(value = {"idadmin"}, allowSetters = true)
	@JsonIgnore
	@OneToMany(mappedBy = "idadmin")
	private List<Client> clients;
	
	
	public Admin() {
		
	}
	

	public Admin(Long id, String user, String password, String email,List<Client> clientss) {
		this.user = user;
		this.password = password;
		this.email = email;
		this.clients=clientss;
	}

	public String getUser() {
		return user;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", password=" + password + ", email=" + email + ", clients=" + clients + "]";
	}
	
	
}

