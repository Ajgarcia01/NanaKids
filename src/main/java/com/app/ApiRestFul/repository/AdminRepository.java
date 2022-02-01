package com.app.ApiRestFul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	//aquí van las consultas
}
