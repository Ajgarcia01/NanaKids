package com.app.ApiRestFul.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "client")
public class Client {

	//private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "Type")
	private boolean type; //true padre, false madre
	@Column(name = "Name")
	private String Name;
	@Column(name = "Surname")
	private String Surname;
	@Column(name = "Phone",length = 9)
	private int Phone;
	@Column(name = "Email")
	private String Email;
	@JsonIgnoreProperties(value = {"client"}, allowSetters = true)
	@ManyToMany(mappedBy = "client", cascade = CascadeType.MERGE)
	private List<Kid> kid;
	@JsonIgnoreProperties(value = {"clients"}, allowSetters = true)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_admin")
	private Admin admin;
	
	
	public Client(){
	
	}

	public Client(String id, boolean type, String name, String surname, int phone, String email,Admin user_admin,List<Kid> kid) {
		this.id = id;
		this.type = type;
		this.Name = name;
		this.Surname = surname;
		this.Phone = phone;
		this.Email = email;
		this.admin= user_admin;
		this.kid=kid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin user_admin) {
		this.admin = user_admin;
	}
	
	
	
	public List<Kid> getKid() {
		return kid;
	}

	public void setKid(List<Kid> kid) {
		this.kid = kid;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", type=" + type + ", Name=" + Name + ", Surname=" + Surname + ", Phone=" + Phone
				+ ", Email=" + Email +", admin=" + admin + "]";
	}
	
	
}
