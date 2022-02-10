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

@Service
public class FelicitationService {
	@Autowired
	FelicitationRepository repository;
	
	private static final Logger log4 = LoggerFactory.getLogger(KidService.class);

	//		-->	OBTENER TODAS LAS FELICITACIONES	<--
	public List<Felicitation> getAllFelicitations() {
		
		List<Felicitation> result = repository.findAll();
		
		if (!result.isEmpty()) {
			log4.info("Se han obtenido todas las Felicitaciones con éxito");
			return result;
		} else {
			log4.error("ERROR: No se han encontrado Felicitaciones en la base de datos");
			throw new RecordNotFoundException("No hay valores","");
		}
	}

	//		-->	OBTENER FELICITACIONES POR ID		<--
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
	
	//		-->	CREAR FELICITACION	<--	
	public Felicitation createFelicitation(Felicitation felicitation) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (felicitation != null) {
			if (felicitation != null && felicitation.getId() < 0) {
				try {
					felicitation = repository.save(felicitation);
					log4.info("Felicitacion creada con éxito");
					return felicitation;
				} catch (IllegalArgumentException e) {
					log4.error("Se han recibido datos incorrectos al crear la Felicitaciones, ERROR: " + e);
					throw new IllegalArgumentException(
							"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
				}
			} else {
				return UpdateFelicitation(felicitation);
			}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear la Felicitaciones");
			throw new NullPointerException("Valor nulo");
		}

	}
	
	//		-->	ACTUALIZAR FELICITACION	<--
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
	
	//		-->	OBTENER LISTA DE FELICITACIONES POR TIPO	<--
	
	public List<Felicitation> listTypeFelicitation(int TypeFelicitation) {
		
		List<Felicitation> listFelicitation = repository.getByType(TypeFelicitation);
		
			if(listFelicitation != null) {
				
				log4.info("Se han obtenido todas las Felicitaciones de tipo: "+TypeFelicitation+ " con éxito");
				return listFelicitation;
			}else {
				
				log4.error("ERROR: No se han encontrado Felicitaciones de tipo: "+TypeFelicitation+" en la base de datos");
				throw new RecordNotFoundException("No hay valores","");
			}
			
		
	}
	
	//	-->	OBTENER LISTA DE FELICITACIONES CON LA FECHA ACTUAL Y TIPO INTRODUCIDO	<--
	
	public List<Felicitation> listFelicitationByDateAndType(LocalDate now , int type){
		
		List<Felicitation> listFelicitation = repository.getListIDByLocalDateNowANDtype(now, type);

		if(listFelicitation != null) {
			
			log4.info("Se han obtenido todas las Felicitaciones de tipo: "+type+ " y fecha  "+now+" con éxito");
			return listFelicitation;
		}else {
			log4.error("ERROR: No se han encontrado Felicitaciones de tipo: "+type+" y fecha  "+now+" en la base de datos");
			throw new RecordNotFoundException("No hay valores","");
		}
	}

	//		-->	OBTENER NUMERO DE FELICITACIONES TOTALES	<--
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
		
		//		-->	OBTENER NUMERO DE FELICITACIONES CON FECHA x Y TIPO x	<--
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
		
		//		FALTAN LOS SERVICIOS QUE IMPLIQUEN EL ESTADO DE LA FELICITACION
	
	//		-->	BORRAR FELICITACION POR ID	<--
	
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
