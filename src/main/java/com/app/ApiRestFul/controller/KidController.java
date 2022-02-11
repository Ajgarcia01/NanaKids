package com.app.ApiRestFul.controller;

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
 * @author=Jes�s Garc�a Luque 
 */

@RestController
@RequestMapping("/kid")
public class KidController {
	@Autowired
	KidService service;

	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos los ni�os que
	 *         existan en la BBDD a trav�s del servicio
	 */
	@GetMapping
	public ResponseEntity<List<Kid>> getAllKids() {
		try {
			List<Kid> all = service.getAllKids();
			return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
		} catch (ResponseStatusException e) {
			List<Kid> all = service.getAllKids();
			return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
		}
	}

	/**
	 * @param name
	 * @return lista de ni�os en concreto de la BBDD con el nombre pasado por
	 *         par�metro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
	@GetMapping("/search/{name}")
	public ResponseEntity<List<Kid>> getKidByName(@PathVariable("name") String name) throws ResponseStatusException {
		if (name != null) {
			try {
				List<Kid> all = service.getKidByName(name);
				return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"El ni�o con nombre: " + name + "no se ha encontrado", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return un ni�o en concreto de la BBDD con el id pasado por par�metro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Kid> getKidById(@PathVariable("id") Long id) throws ResponseStatusException {

		if (id != null && id > -1) {
			try {
				Kid kid = service.getKidById(id);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o con id: " + id + "no se ha encontrado",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param n
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un nuevo ni�o creado en
	 *         la BBDD
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
	@PostMapping
	public ResponseEntity<Kid> createKid(@RequestBody Kid n) throws ResponseStatusException {
		if (n != null) {
			try {
				Kid kid = service.createKid(n);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido guardado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
	}

	/**
	 * @param n
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un ni�o actualizado en
	 *         la BBDD
	 * @throws ResponseStatusException (nos devolveria unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
	@PutMapping
	public ResponseEntity<Kid> UpdateKid(@RequestBody Kid n) throws ResponseStatusException {
		if (n != null && n.getId() > 0) {
			try {
				Kid kid = service.Update(n);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido actualizado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un ni�o eliminado en la
	 *         BBDD
	 * @throws ResponseStatusException (En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
	@DeleteMapping("/{id}")
	public HttpStatus deleteKid(@PathVariable("id") Long id) throws ResponseStatusException {
		if (id != null && id > -1) {
			try {
				service.deleteKidById(id);
				return HttpStatus.OK;
			} catch (ResponseStatusException e) {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El ni�o no ha sido eliminado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}
}
