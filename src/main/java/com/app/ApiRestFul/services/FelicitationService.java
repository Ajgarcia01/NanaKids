package com.app.ApiRestFul.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.repository.FelicitationRepository;

@Service
public class FelicitationService {
	@Autowired
	FelicitationRepository repository;
	//OBTENER TODAS LAS FELICITACIOS
		public List<Felicitation> getAllFelicitations(){
			List<Felicitation> result = repository.findAll();
			return result;
		}
		//OBTENER FELICITACIONES POR ID 
		public Felicitation getFelicitationById(Long id){
			Optional<Felicitation> result = repository.findById(id);
			if (result.isPresent()) {
				return result.get();
				
			}else {	
				throw new RecordNotFoundException("", id);
			}
			
		}
		//CREAR O ACTUALIZAR FELICITACION
		
		/*
		public Felicitation createOrUpdate(Felicitation felicitation) {
			if ()
		}
		*/
		
		//BORRAR FELICITACION POR ID
		public void deleteFelicitation(Long id) {
			
			Optional<Felicitation> result  = repository.findById(id);
			if(result.isPresent()) {
				repository.deleteById(id);
			}else {
				//NOTA BORRADA POR ID
			}
		}
		
}
