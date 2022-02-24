package com.app.ApiRestFul.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.repository.FelicitationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author= Gonzalo Bretones-Mora Quero 
 */

@Service
public class FelicitationService {
	@Autowired
	FelicitationRepository repository;
	
	private static final Logger log4 = LoggerFactory.getLogger(FelicitationService.class);

	
	/**
	 * -->	OBTENER TODAS LAS FELICITACIONES	<--	
	 * 
	 * 
	 * @return Devuelve una lista de todas las felicitaciones de la base de datos
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	public List<Felicitation> getAllFelicitations() {
		
		List<Felicitation> result = repository.findAll();
		
		if (!result.isEmpty()) {
			log4.info("Se han obtenido todas las Felicitaciones con �xito");
			return result;
		} else {
			log4.error("ERROR: No se han encontrado Felicitaciones en la base de datos");
			throw new RecordNotFoundException("No hay valores","");
		}
	}

			
	/**
	 * -->	OBTENER FELICITACIONES POR ID	<--	
	 * 
	 * @param id Identidicador de la felicitacion
	 * @return Devuelve una felicitacion de la base de datos por un ID
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	public Felicitation getFelicitationById(Long id) throws RecordNotFoundException {
		
		if (id != null) {
			try {
				Optional<Felicitation> result = repository.findById(id);
				if (result.isPresent()) {
					log4.info("INFO: Felicitacion con id " + id + " ha sido encontrada en la base de datos");
					return result.get();
				} else {
					log4.info("ERROR: No hay resultados en obtener una Felicitacion para el id: " + id);
					throw new RecordNotFoundException("No se han encontrado valores para el id: ", id);
				}
			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar una Felicitacion por id no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar una Felicitacion por id son nulos");
			throw new NullPointerException("Valor nulo");
		}
		
	}
	
	
	/**
	 * -->	CREAR FELICITACION	<--	
	 * 
	 * @param felicitation Felicitation que vamos a anadir a la base de datos , fotoNube String corresponde a la url de la 
	 * foto guardada en cloudinary
	 * 
	 * @return Devuelve la felicitacion
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	public Felicitation createFelicitation(Felicitation felicitation , String fotoNube ) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (felicitation != null) {
				
				felicitation.setImage(fotoNube);
				try {
					felicitation = repository.createFelicitation(felicitation.getType(), 
							felicitation.getImage(), felicitation.getKid().getId(),felicitation.getKid().getBirthDate());
					log4.info("Felicitacion creada con �xito");
					return felicitation;
				} catch (IllegalArgumentException e) {
					log4.error("Se han recibido datos incorrectos al crear la Felicitaciones, ERROR: " + e);
					throw new IllegalArgumentException(
							"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
				}
			

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear la Felicitaciones");
			throw new NullPointerException("Valor nulo");
		}

	}
	
	
	/**
	 * -->	ACTUALIZAR FELICITACION	<--	
	 * 
	 * @param felicitation Felicitation que vamos a actualizar de la base de datos
	 * @return Devuelve la felicitacion
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	public Felicitation UpdateFelicitation(Felicitation felicitation) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (felicitation != null) {
			try {
				Optional<Felicitation> n = repository.findById(felicitation.getId());
				if (n.isPresent()) { // UPDATE
					Felicitation newKid = n.get(); 
					Felicitation newFelicitation = n.get();
					newFelicitation.setId(felicitation.getId());
					newFelicitation.setType(felicitation.getType());
					newFelicitation.setEstate(felicitation.isEstate());
					newFelicitation.setDateSend(felicitation.getDateSend());
					newFelicitation.setImage(felicitation.getImage());
					newFelicitation.setKid(felicitation.getKid());
					newFelicitation = repository.save(newFelicitation);
					
					return newKid;

				} else {
					log4.error("ERROR: Se han recibido datos incorrectos al crear la Felicitacion");
					throw new IllegalArgumentException("Los valores introducidos no son correctos");
				}
			} catch (Exception e) {
				log4.error("ERROR: valores mal introducidos al actulizar una Felicitaciones");
				throw new RecordNotFoundException("Los valores introducidos no son correctos","");
			}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear la Felicitaciones");
			throw new NullPointerException("Valor nulo");
		}
	}
	
	
	/**
	 * -->	OBTENER LISTA DE FELICITACIONES POR TIPO	<--	
	 * 
	 * @param TypeFelicitation Tipo de felicitacion 
	 * @return Devuelve una lista de  felicitaciones
	 * 
	 *         En caso de error nos devolveria un mensaje de error
	 */
	
	public List<Felicitation> listTypeFelicitation(int TypeFelicitation) {
		
		List<Felicitation> listFelicitation = repository.getByType(TypeFelicitation);
		
			if(listFelicitation != null) {
				
				log4.info("Se han obtenido todas las Felicitaciones de tipo: "+TypeFelicitation+ " con �xito");
				return listFelicitation;
			}else {
				
				log4.error("ERROR: No se han encontrado Felicitaciones de tipo: "+TypeFelicitation+" en la base de datos");
				throw new RecordNotFoundException("No hay valores","");
			}
			
		
	}
	
	
	/**
	 * -->	OBTENER LISTA DE FELICITACIONES POR FECHA  Y TIPO 	<--	
	 * 
	 * @param now LocalDate (Fecha actual ) , type Tipo de felicitacion 
	 * @return Devuelve una lista de  felicitaciones
	 * 
	 *         En caso de error nos devolveria un mensaje de error
	 */
	
	public List<Felicitation> listFelicitationByDateAndType(LocalDate now , int type){
		
		List<Felicitation> listFelicitation = repository.getListIDByLocalDateNowANDtype(now, type);

		if(listFelicitation != null) {
			
			log4.info("Se han obtenido todas las Felicitaciones de tipo: "+type+ " y fecha  "+now+" con �xito");
			return listFelicitation;
		}else {
			log4.error("ERROR: No se han encontrado Felicitaciones de tipo: "+type+" y fecha  "+now+" en la base de datos");
			throw new RecordNotFoundException("No hay valores","");
		}
	}

	
	/**
	 * -->	OBTENER NUMERO DE FELICITACIONES TOTALES 	<--	
	 * 
	 * 
	 * @return Devuelve el numero de felicitaciones totales
	 * 
	 *         En caso de error nos devolveria el mensaje: Hay 0 Felicitaciones
	 */
	
		public Long numberFelicitations() {
			Long number = repository.count();
			if(number > 0) {
				log4.info("Hay "+number+" Felicitaciones en la base de datos.");
				return number;
			}else {
				log4.error("Hay 0 Felicitaciones en la base de datos.");
				throw new RecordNotFoundException("No hay valores","");
			}
			
		}
		
		
	/**
	 * -->	OBTENER NUMERO DE FELICITACIONES CON FECHA  Y TIPO  	<--	
	 * 
	 * @param now LocalDate (Fecha actual) , type Tipo de felicitacion 
	 * @return Devuelve el numero de felicitaciones filtradas por la fecha actual y un tipo
	 * 
	 *         En caso de error nos devolveria el mensaje: Hay 0 Felicitaciones con esos parametros.
	 */
	public Long numberFelicitationsByDateAndType(LocalDate now , int type) {
			
			Long number = repository.getCountListIDByLocalDateNowANDtype(now, type);
			if(number > 0) {
				log4.info("Hay "+number+" Felicitaciones con fecha: "+now+" y de tipo: "+type+" en la base de datos.");
				return number;
			}else {
				log4.error("Hay 0 Felicitaciones con fecha: "+now+" y de tipo: "+type+"en la base de datos.");
				throw new RecordNotFoundException("No hay valores","");
			}
			
	}
		
	
	/**
	 * -->	CAMBIAR ESTADOEL ESTADO DE LAS FELICITACIONES A: ENVIADO	<--	
	 * 
	 * @param type Tipo , now LocalDate (Fecha actual)
	 * 
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	
	public void changeToSent(int type , LocalDate now) {
			
			if (type > 0 && now != null) {
				repository.changeToSent(type, now);
				log4.info("Felicitaciones enviadas correctamente. FECHA:"+now+"TIPO: "+type);
			}else {
				log4.error("ERROR: Valores nulos introducidos");
				throw new NullPointerException();
			}
			
	}
		
	/**
	 * -->	CAMBIAR EL ESTADO DE LAS FELICITACIONES A: NO ENVIADO	<--	
	 * 
	 * @param type Tipo , now LocalDate (Fecha actual)
	 * 
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	
	public void changeToUnsent(int type , LocalDate now) {
			if (type > 0 && now != null) {
				repository.changeToUnsent(type, now);
				log4.info("Felicitaciones cambiadas a no enviado correctamente. FECHA:"+now+"TIPO: "+type);
			}else {
				log4.error("ERROR: Valores nulos introducidos");
				throw new NullPointerException();
			}
	}
		
	
	/**
	 * -->	BORRAR FELICITACION POR ID	<--
	 * 
	 * @param id Identificador del administrador
	 * 
	 *           En caso de error nos devolveria un NullPointerException, ya que no
	 *           hay nada, la busqueda seria nula
	 */
	
	public void deleteFelicitationById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null && id > -1) {
			try {
				Optional<Felicitation> kid = repository.findById(id);
				if (kid.isPresent()) {
					log4.info("INFO: Felicitacion con id: " + id + " eliminada.");
					repository.deleteById(id);

				}
			} catch (Exception e) {
				log4.error("ERROR: Valores nulos introducidos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.error("ERROR: Valores nulos introducidos");
			throw new NullPointerException();
		}
	}
	
	

}
