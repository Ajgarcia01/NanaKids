package com.app.ApiRestFul.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Felicitation;

@Repository
public interface FelicitationRepository extends JpaRepository<Felicitation,Long>{
	//aqu� van las consultas
	
	//Obtener lista de felicitaciones por tipo
	
	@Query(value = "SELECT * FROM felicitation AS f WHERE f.type LIKE %?1%",nativeQuery = true)
    List<Felicitation> getByType(int type);
	
	/*
	 * CONSULTA PARA OBTENER EL ID DE LA FELICITACION CON LA FECHA PASADA COMPARANDO DIA Y MES.
	 * 
	 * -->	LISTA DE ID	<--
	SELECT id,DAY (date_send) AND MONTH (date_send) FROM felicitation where date_send LIKE '2022-02-08'
	*/
	
	@Query(value = "SELECT id,DAY (date_send) AND MONTH (date_send) FROM felicitation where date_send LIKE ?1",nativeQuery = true)
    List<Long>  getListIDByLocalDateNow(LocalDate LocalDateNow);
	
	/*
	 * CONSULTA PARA OBTENER TODOS LOS DATOS DE LA FELICITACION CON LA FECHA PASADA COMPARANDO DIA Y MES.
	 * 
	 * -->	LISTA DE FELICITACIONES	<--
	SELECT *,DAY (date_send) AND MONTH (date_send) FROM felicitation where date_send LIKE '2022-02-08'
	*/
	
	@Query(value = "SELECT *,DAY (date_send) AND MONTH (date_send) FROM felicitation where date_send LIKE ?1",nativeQuery = true)
    List<Felicitation>  getListFelicitationByLocalDateNow(LocalDate LocalDateNow);
	
	/*CONSULTA PARA OBTENER LA FELICITACIONES , CON LA FECHA ACTUAL Y DEL TIPO PASADO
	 *
	 *-->	LISTA DE ID , POR FECHA Y POR TYPE	<--
	SELECT *,DAY (date_send) AND MONTH (date_send) FROM felicitation  where (date_send LIKE '2022-02-08')AND (type LIKE 1)
	*/
	@Query(value = "SELECT id,DAY (date_send) AND MONTH (date_send) FROM felicitation  where (date_send LIKE ?1)AND (type LIKE ?2)",nativeQuery = true)
    List<Long>  getListIDByLocalDateNowANDtype(LocalDate LocalDateNow , int type);
	
}
