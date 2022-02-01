package com.app.ApiRestFul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Felicitation;

@Repository
public interface FelicitationRepository extends JpaRepository<Felicitation,Long>{
	//aquí van las consultas
	
	//Obtener lista de felicitaciones por tipo
	
	
	@Query(value = "SELECT * FROM felicitation AS f WHERE f.tipe LIKE %?1%",nativeQuery = true)
	    List<Felicitation> getByType(int type);
	
	@Query(value = "UPDATE felicitation SET estate = [estate = %?1%] [WHERE id = %?1%]" , nativeQuery = true)
	Felicitation  updateStatus(boolean status , Long id);
}
