package com.app.ApiRestFul.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "client")
public class Client implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "dni")
	private String dni;
	@Column(name = "Type")
	private boolean type; // true padre, false madre
	@Column(name = "Name")
	private String Name;
	@Column(name = "Surname")
	private String Surname;
	@Column(name = "Phone", length = 9)
	private String Phone;
	@Column(name = "Email")
	private String Email;
	@JsonIgnoreProperties(value = { "client" }, allowSetters = true)
	@ManyToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Kid> kid;
	
	@JsonIgnoreProperties(value = { "clients" }, allowSetters = true)
	@JoinColumn(name = "user_admin")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Admin idadmin;



	public Client(Long id, String dni, boolean type, String name, String surname, String phone, String email,
			List<Kid> kid, Admin idadmin) {
		this.id = id;
		this.dni = dni;
		this.type = type;
		Name = name;
		Surname = surname;
		Phone = phone;
		Email = email;
		this.kid = kid;
		this.idadmin = idadmin;
	}

	public String getId() {
		return dni;
	}

	public void setId(String dni) {
		this.dni = dni;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public List<Kid> getKid() {
		return kid;
	}

	public void setKid(List<Kid> kid) {
		this.kid = kid;
	}

	public Admin getAdmin() {
		return idadmin;
	}

	public void setAdmin(Admin admin) {
		this.idadmin = admin;
	}

	@Override
	public String toString() {
		return "Client [id=" + dni + ", type=" + type + ", Name=" + Name + ", Surname=" + Surname + ", Phone=" + Phone
				+ ", Email=" + Email + ", admin=" + idadmin + "]";
	}

}

