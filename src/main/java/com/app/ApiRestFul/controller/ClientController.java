package com.app.ApiRestFul.controller;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.services.ClientService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService service;
	// antes de tocar esto tener el servicio hecho

	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos los clientes que
	 *         existan en la BBDD a través del servicio
	 */
	@GetMapping
	public ResponseEntity<List<Client>> getAllClient() {
		try {
			List<Client> list = service.getAllClient();
			return new ResponseEntity<List<Client>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Client>>(new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @param id
	 * @return cliente en concreto de la BBDD con el ID pasado por parámetro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") String id)
			throws RecordNotFoundException, ResponseStatusException {

		if (id != null) {
			try {
				Client admin = service.getClientById(id);
				return new ResponseEntity<Client>(admin, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente con id: " + id + "no se ha encontrado",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param client
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un nuevo cliente creado
	 *         en la BBDD
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client)
			throws RecordNotFoundException, ResponseStatusException {
		if (client != null) {
			try {
				Client newClient = service.createClient(client);
				return new ResponseEntity<Client>(newClient, new HttpHeaders(), HttpStatus.OK);

			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no ha sido guardado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param client
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un cliente actualizado
	 *         en la BBDD
	 * @throws ResponseStatusException (nos devolveria unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
	@PutMapping
	public ResponseEntity<Client> UpdateClient(@RequestBody Client client)
			throws RecordNotFoundException, ResponseStatusException {
		if (client != null) {
			try {
				Client newClient = service.createClient(client);
				return new ResponseEntity<Client>(newClient, new HttpHeaders(), HttpStatus.OK);

			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no ha sido actualizado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un cliente eliminado en
	 *         la BBDD
	 * @throws ResponseStatusException (En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
	@DeleteMapping("/{id}")
	public HttpStatus deleteClientById(@PathVariable("id") String id)
			throws RecordNotFoundException, ResponseStatusException {

		if (id != null) {
			try {
				service.deleteClient(id);
				return HttpStatus.ACCEPTED;
			} catch (ResponseStatusException e) {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no ha sido eliminado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

}
