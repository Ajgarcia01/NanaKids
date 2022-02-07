package com.app.ApiRestFul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Kid;

@Repository
public interface KidRepository extends JpaRepository<Kid,Long>{
	//aquí van las consultas
	
	//buscar a los niños por el nombre
	@Query(value = "SELECT * FROM KID AS a WHERE a.name LIKE %?1%",nativeQuery = true)
	List<Kid> getByName(String title);
	
	
	//buscar los padres de un niño
    @Query(value = "SELECT * FROM kid,client_kid AS ck,client AS c WHERE kid.id=ck.id_kid AND ck.id_client=c.id AND kid.id=?1",nativeQuery = true)
    List<Client> getClients(Long id);
   
	
}
