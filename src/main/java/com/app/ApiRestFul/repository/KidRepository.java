package com.app.ApiRestFul.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Kid;


/*
 * @author=Jesús García Luque 
 */


@Repository
public interface KidRepository extends JpaRepository<Kid,Long>{
	//aquí van las consultas
	
	//Consulta para buscar a los niños por el nombre (filtro de búsqueda)
	@Query(value = "SELECT * FROM KID AS a WHERE a.name LIKE %?1%",nativeQuery = true)
	List<Kid> getByName(String title);

   
	
}
