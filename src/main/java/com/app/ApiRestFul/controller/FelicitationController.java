package com.app.ApiRestFul.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.services.FelicitationService;

@RestController
@RequestMapping("/felicitation")
public class FelicitationController {

	
//	FALTAN LOS CONTROLLERS QUE IMPLIQUEN EL ESTADO DE LA FELICITACION
	@Autowired
	FelicitationService service;
	// antes de tocar esto tener el servicio hecho
	
			//*
			//		--->		GET			<---
			//*
	
	//	-->OBTENER TODAS LAS FELICITACIONES	<--
	@GetMapping
	public ResponseEntity<List<Felicitation>> getAllFelicitations() {
		List<Felicitation> list = service.getAllFelicitations();
		return new ResponseEntity<List<Felicitation>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	//	-->OBTENER FELICITACION POR ID	<--
	@GetMapping("/{id}")
	public ResponseEntity<Felicitation> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		if (id != null && id > -1) {
			try {
				
				Felicitation entity = service.getFelicitationById(id);
				return new ResponseEntity<Felicitation>(entity, new HttpHeaders(), HttpStatus.OK);
				
			}catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La Felicitacion con id: " + id + "no se ha encontrado",e);
			}
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
			
	}
	
	
	//	-->OBTENER LISTA DE FELICITACIONES POR TIPO	<--
	@GetMapping("/search/{type}")
	public ResponseEntity<List<Felicitation>> getListByType( @PathVariable("type")int type){
		try {
			
		List<Felicitation> list  =  service.listTypeFelicitation(type);
		return new ResponseEntity<List<Felicitation>>(list , new HttpHeaders() , HttpStatus.OK);
		
		} catch (ResponseStatusException e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Las Felicitaciones con tipo: " + type + "no se ha encontrado", e);
		}
	}
	
	//	-->	OBTENER LISTA DE TODAS LAS FELICITACIONES CON FECHA DE HOY Y DEL TIPO DEFINIDO		<--
	@GetMapping("/searchByDateAndType/{type}")
	public ResponseEntity<List<Felicitation>> getlistFelicitationByDateAndType(@PathVariable("type")int type ){
		try {
		List<Felicitation> list  =  service.listFelicitationByDateAndType(LocalDate.now(), type);
		return new ResponseEntity<List<Felicitation>>(list , new HttpHeaders() , HttpStatus.OK);
		
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Las Felicitaciones con tipo: " + type +" y en con fecha "+LocalDate.now()+ " no se ha encontrado", e);
		}
	}
	
	//	-->	OBTENER EL NUMERO DE FELICITACIONES TOTALES	<--
	@GetMapping("/count")
	public ResponseEntity<Long> getCount(){
		try {
		Long count = service.numberFelicitations();
		return new ResponseEntity<Long>(count , new HttpHeaders() , HttpStatus.OK);
		
		}catch (ResponseStatusException e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No se han obtenido el numero de felicitaciones", e);
		}
	}
	
	//	-->	OBTENER NUMERO DE FELICITACIONES CON FECHA x Y TIPO x	<--
	@GetMapping("/countByDateAndType/{type}")
	public ResponseEntity<Long> getCountFelicitationByDateAndType(@PathVariable("type")int type){
		try {
			Long count = service.numberFelicitationsByDateAndType(LocalDate.now(),type);
		return new ResponseEntity<Long>(count , new HttpHeaders() , HttpStatus.OK);
		
		}catch (ResponseStatusException e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No se han obtenido el numero de felicitaciones con tipo: "+type+" y en con fecha "+LocalDate.now()+" .", e);
		}
	}
	
	
			//*
			//	--->		POST			<---
			//*
	
	
	//	-->	CREAR FELICITACION 	<--
	@PostMapping
	public ResponseEntity<Felicitation> createFelicitation(@RequestBody Felicitation f) throws ResponseStatusException {
		if (f != null) {
			try {
				Felicitation felicitation = service.createFelicitation(f);
				return new ResponseEntity<Felicitation>(felicitation, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido guardada correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
	}
	
	
			//*
			//	--->		PUT			<---
			//*
	
	
	//	-->	ACTUALIZAR FELICITACION	<--
	@PutMapping
	public ResponseEntity<Felicitation> UpdateKid(@RequestBody Felicitation f) throws ResponseStatusException {
		if (f != null && f.getId() > 0) {
			try {
				Felicitation felicitation = service.UpdateFelicitation(f);
				return new ResponseEntity<Felicitation>(felicitation, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido actualizada correctamente",e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}
	
	
			//*
			//	--->		DELETE			<---
			//*
	
	
	//	-->	BORRAR FELICITACION POR ID	<--
	@DeleteMapping("/{id}")
	public HttpStatus deleteFelicitation(@PathVariable("id") Long id) throws ResponseStatusException {
		if (id != null && id > -1) {
			try {
				service.deleteFelicitationById(id);
				return HttpStatus.OK;
			} catch (ResponseStatusException e) {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido eliminada correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}
	
	

	
	

}
