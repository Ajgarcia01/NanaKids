package com.app.ApiRestFul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
	//aqui van las consultas
}
