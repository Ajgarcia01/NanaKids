package com.app.ApiRestFul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Felicitation;

@Repository
public interface FelicitationRepository extends JpaRepository<Felicitation,Long>{
	//aqu� van las consultas
}
