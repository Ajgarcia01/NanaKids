package com.app.ApiRestFul.services;

import java.util.ArrayList;
import java.util.Date;
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

	// OBTENER TODAS LAS FELICITACIONES
	public List<Felicitation> getAllFelicitations() {
		List<Felicitation> result = repository.findAll();
		return result;
	}

	// OBTENER FELICITACIONES POR ID
	public Felicitation getFelicitationById(Long id) throws RecordNotFoundException {
		Optional<Felicitation> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("", id);
		}

	}
	
	// ACTUALIZAR UNA FELICITACION Y SI NO EXISTE LA CREA	
	public Felicitation Update(Felicitation felicitation) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (felicitation.getId() != null && felicitation.getId() > 0) {
			Optional<Felicitation> n = repository.findById(felicitation.getId());
			if (n.isPresent()) { // UPDATE
				Felicitation newFelicitation = n.get();
				newFelicitation.setId(felicitation.getId());
				newFelicitation.setType(felicitation.getType());
				newFelicitation.setEstate(felicitation.isEstate());
				newFelicitation.setDateSend(felicitation.getDateSend());
				newFelicitation.setImage(felicitation.getImage());
				newFelicitation.setKid(felicitation.getKid());
				newFelicitation = repository.save(newFelicitation);
				return newFelicitation;
			} else { // INSERT
				felicitation.setId(null);
				felicitation = repository.save(felicitation);
				return felicitation;
			}
		} else {
			felicitation = repository.save(felicitation);
			return felicitation;
		}
	}
	
	//OBTENER LISTA DE FELICITACIONES POR TIPO
	
	public List<Felicitation> listTypeFelicitation(int TypeFelicitation) {
		List<Felicitation> listFelicitation = repository.getByType(TypeFelicitation);
		return listFelicitation;
	}

	
	// BORRAR FELICITACION POR ID
	public void deleteFelicitation(Long id) {

		Optional<Felicitation> result = repository.findById(id);
		if (result.isPresent()) {
			repository.deleteById(id);
		} else {
			// NOTA BORRADA POR ID
		}
	}
	
	// OBTENER NUMERO DE FELICITACIONES TOTALES
	public Long numberFelicitations() {
		Long number = repository.count();
		return number;
	}

}
