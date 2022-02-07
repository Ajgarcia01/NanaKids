package com.app.ApiRestFul.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Kid;


/*
 * @author=Jes�s Garc�a Luque 
 */


@Repository
public interface KidRepository extends JpaRepository<Kid,Long>{
	//aqu� van las consultas
	
	//Consulta para buscar a los ni�os por el nombre (filtro de b�squeda)
	@Query(value = "SELECT * FROM KID AS a WHERE a.name LIKE %?1%",nativeQuery = true)
	List<Kid> getByName(String title);

   
	
}
