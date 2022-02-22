package com.app.ApiRestFul.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Kid;


/*
 * @author=Jesus Garcia Luque 
 */


@Repository
public interface KidRepository extends JpaRepository<Kid,Long>{
	//aqui van las consultas
	
	//Consulta para buscar a los ninos por el nombre (filtro de busqueda)
	@Query(value = "SELECT * FROM KID AS a WHERE a.name LIKE %?1%",nativeQuery = true)
	List<Kid> getByName(String title);

   
	
}
