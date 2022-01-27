package com.app.ApiRestFul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Kid;

@Repository
public interface KidRepository extends JpaRepository<Kid,Long>{
	//aquí van las consultas
}
