package com.app.ApiRestFul.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.ApiRestFul.model.Felicitation;

/*
 * @author= Gonzalo Bretones-Mora Quero 
 */

@Repository
public interface FelicitationRepository extends JpaRepository<Felicitation,Long>{
	
	/*
	 * CONSULTA PARA OBTENER LISTA DE FELICITACIONES POR TIPO
	 * 
	 * -->	LISTA DE FELICITACIONES POR TIPO	<--
	*/
	
	@Query(value = "SELECT * FROM felicitation AS f WHERE f.type LIKE %?1%",nativeQuery = true)
    List<Felicitation> getByType(int type);
	
	/*
	 * CONSULTA PARA OBTENER EL ID DE LA FELICITACION CON LA FECHA PASADA COMPARANDO DIA Y MES.
	 * 
	 * -->	LISTA DE ID BY LOCALDATE NOW	<--
	*/
	
	@Query(value = "SELECT id,DAY (date_send) AND MONTH (date_send) FROM felicitation where date_send LIKE ?1",nativeQuery = true)
    List<Long>  getListIDByLocalDateNow(LocalDate LocalDateNow);
	
	/*
	 * CONSULTA PARA OBTENER TODOS LOS DATOS DE LA FELICITACION CON LA FECHA PASADA COMPARANDO DIA Y MES.
	 * 
	 * -->	LISTA DE FELICITACIONES BY LOCALDATE NOW	<--
	*/
	
	@Query(value = "SELECT *,DAY (date_send) AND MONTH (date_send) FROM felicitation where date_send LIKE ?1",nativeQuery = true)
    List<Felicitation>  getListFelicitationByLocalDateNow(LocalDate LocalDateNow);
	
	/*CONSULTA PARA OBTENER LA FELICITACIONES , CON LA FECHA ACTUAL Y DEL TIPO PASADO
	 *
	 *-->	LISTA DE ID , POR LOCALDATE NOW Y POR TYPE	<--
	*/
	@Query(value = "SELECT *,DAY (date_send) AND MONTH (date_send) FROM felicitation  where (date_send LIKE ?1)AND (type LIKE ?2)",nativeQuery = true)
    List<Felicitation>  getListIDByLocalDateNowANDtype(LocalDate LocalDateNow , int type);
	
	/*
	 * Consulta para obetener el numero de felicitaciones que se enviaran el dia X del tipo X
	 * 
	 * -->	NUMERO DE FELICITACIONES , POR LOCALDATE NOW Y POR TYPE	<--
	*/
	
	@Query(value = "SELECT count(id), DAY (date_send) AND MONTH (date_send) FROM felicitation where (date_send LIKE ?1)AND (type LIKE ?2)",nativeQuery = true)
	Long getCountListIDByLocalDateNowANDtype(LocalDate LocalDateNow , int type);
	
	//FALTAN LAS CONSULTAS QUE IMPLIQUEN EL ESTADO DE LA FELICITACION
	
	/*
	 * Consulta para cambiar el estado de la felicitacion a ENVIADO
	 * que se enviaran el dia X del tipo X y su estado sea no enviado
	 * 
	 * 	-->CAMBIAR ESTADO A ENVIADO<--
	 * 
	 */
	@Query(value = "UPDATE felicitation SET estate=1 WHERE (type LIKE ?1)AND (estate=0) AND (DATE_FORMAT(date_send, '%m - %d') = DATE_FORMAT(?2,'%m - %d'))",nativeQuery = true)
	void changeToSent(int type, LocalDate LocalDateNow );
	
	/*
	 * Consulta para cambiar el estado de la felicitacion a NO ENVIADO
	 * del dia X del tipo X y su estado sea enviado
	 * 
	 * 	-->CAMBIAR ESTADO A NO ENVIADO<--
	 * 
	 */
	@Query(value = "UPDATE felicitation SET estate=0 WHERE (type LIKE ?1)AND (estate=1) AND (DATE_FORMAT(date_send, '%m - %d') = DATE_FORMAT(?2,'%m - %d'))",nativeQuery = true)
	void changeToUnsent(int type, LocalDate LocalDateNow );
	
	/*
	 * Consulta para crear una felicitacion con la fecha del nino y el estado a false por defecto
	 * 
	 * -->CREAR FELICITACION<--
	 * 
	 */
	@Query(value = "INSERT INTO `felicitation`(`estate`, `type`, `image`, `id_kid`, `date_send`) VALUES (0,?1,?2,?3,?4)",nativeQuery = true)
	Felicitation createFelicitation(int type, String image , Long id_kid , LocalDate date_sebd );
	
	
}
