package com.app.ApiRestFul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ApiRestFul.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	//aqu� van las consultas
}
