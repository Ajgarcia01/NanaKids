package com.app.ApiRestFul.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.model.Kid;
import com.app.ApiRestFul.services.KidService;

/*
 * @author=Jesús García Luque 
 */

@RestController
@RequestMapping("/kid")
public class KidController {
	@Autowired
	KidService service;

	/*
	 * EndPoint que nos devuelve un HttpStatus.OK y todos los niños que existan en
	 * la BBDD a través del servicio
	 */

	@GetMapping
	public ResponseEntity<List<Kid>> getAllKids() {
		try {
			List<Kid> all = service.getAllKids();
			return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			List<Kid> all = service.getAllKids();
			return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
		}
	}

	/*
	 * 
	 * EndPoint que nos devuelve un HttpStatus.OK y todos los niños que coincidan con ese nombre
	 * 
	 * 
	 * En caso de error nos devolveria unHttpStatus.NOT_FOUND o HttpStatus.BAD_REQUEST, en función de la petición
	 * 
	 */

	@GetMapping("/search/{name}")
	public ResponseEntity<List<Kid>> getKidByName(@PathVariable("name") String name) {
		if (name != null) {
			try {
				List<Kid> all = service.getKidByName(name);
				return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				List<Kid> all = new ArrayList<Kid>();
				return new ResponseEntity<List<Kid>>(all,new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		} else {
			List<Kid> all = new ArrayList<Kid>();
			return new ResponseEntity<List<Kid>>(all,new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

	/*
	 * 
	 * EndPoint que nos devuelve un HttpStatus.OK y todos el niño que coincidan con ese id
	 * 
	 * 
	 * En caso de error nos devolveria unHttpStatus.NOT_FOUND o HttpStatus.BAD_REQUEST, en función de la petición
	 * 
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Kid> getKidById(@PathVariable("id") Long id) {

		if (id != null && id > -1) {
			try {
				Kid kid = service.getKidById(id);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El niño con id: "+id+"no se ha encontrado", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/*
	 * 
	 * EndPoint que nos devuelve un HttpStatus.OK y un nuevo niño creado en la BBDD
	 * 
	 * 
	 * En caso de error nos devolveria unHttpStatus.NOT_FOUND o HttpStatus.BAD_REQUEST, en función de la petición
	 * 
	 */

	@PostMapping
	public ResponseEntity<Kid> createKid(@RequestBody Kid n)throws ResponseStatusException {
		if (n != null) {
			try {
				Kid kid = service.createKid(n);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El niño no ha sido guardado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
	}

	/*
	 * 
	 * EndPoint que nos devuelve un HttpStatus.OK y un niño actualizado en la BBDD
	 * 
	 * 
	 * En caso de error nos devolveria unHttpStatus.NOT_FOUND o HttpStatus.BAD_REQUEST, en función de la petición
	 * 
	 */

	@PutMapping
	public ResponseEntity<Kid> UpdateKid(@RequestBody Kid n) {
		if (n != null && n.getId() > 0) {
			try {
				Kid kid = service.Update(n);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Kid>(new Kid(),new HttpHeaders(), HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Kid>(new Kid(),new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

	/*
	 * 
	 * EndPoint que nos devuelve un HttpStatus.OK y un niño eliminado en la BBDD
	 * 
	 * 
	 * En caso de error nos devolveria unHttpStatus.NOT_FOUND o HttpStatus.BAD_REQUEST, en función de la petición
	 * 
	 */

	@DeleteMapping("/{id}")
	public HttpStatus deleteKid(@PathVariable("id") Long id) {
		if (id != null && id > -1) {
			try {
				service.deleteKidById(id);
				return HttpStatus.OK;
			} catch (Exception e) {

				return HttpStatus.NOT_FOUND;
			}
		} else {
			return HttpStatus.BAD_REQUEST;
		}

	}
}
