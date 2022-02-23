package com.app.ApiRestFul.controller;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;

import com.app.ApiRestFul.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.services.ClientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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


/*
 * @author=Eduardo Carmona Lopez
 */

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService service;
	// antes de tocar esto tener el servicio hecho

	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos los clientes que
	 *         existan en la BBDD a trav�s del servicio
	 */
    @ApiOperation(value = "Get all client", notes = "Returns a client list")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "ERROR:Can't be found admins"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
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
	 * @return cliente en concreto de la BBDD con el ID pasado por par�metro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
    @ApiOperation(value = "Get a client by id", notes = "Returns a client as per the id")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,client found"),
      @ApiResponse(code = 404, message = "ERROR:Client not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Long id)
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
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
    @ApiOperation(value = "Create a new client", notes = "Returns a new  client")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,client Created"),
      @ApiResponse(code = 404, message = "ERROR:Client can't be created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
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
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
    @ApiOperation(value = "Update a client", notes = "Returns a client updated")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,client Updated"),
      @ApiResponse(code = 404, message = "ERROR:Client can't be update"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@PutMapping
	public ResponseEntity<Client> UpdateClient(@RequestBody Client client)
			throws RecordNotFoundException, ResponseStatusException {
		if (client != null) {
			try {
				Client newClient = service.UpdateClient(client);
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
	 *                                 HttpStatus.BAD_REQUEST, en funci�n de la
	 *                                 petici�n)
	 */
    @ApiOperation(value = "Delete a client", notes = "Delete a client")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,client Deleted"),
      @ApiResponse(code = 404, message = "ERROR:Client can't be delete"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@DeleteMapping("/{id}")
	public HttpStatus deleteClientById(@PathVariable("id") Long id)
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
