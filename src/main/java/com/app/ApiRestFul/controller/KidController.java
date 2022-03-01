package com.app.ApiRestFul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.model.Kid;
import com.app.ApiRestFul.services.KidService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * @author=Jes�s Garc�a Luque 
 */

@RestController
@RequestMapping("/kid")
public class KidController {
	@Autowired
	KidService service;

	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos los ninos que
	 *         existan en la BBDD a traves del servicio
	 */
    @ApiOperation(value = "Get all kids", notes = "Returns a kid list")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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
	 *         parametro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Get a kid by name", notes = "Returns a kid as per the name")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,kid found"),
      @ApiResponse(code = 404, message = "ERROR:Kid not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@GetMapping("/search/{name}")
	public ResponseEntity<List<Kid>> getKidByName(@PathVariable("name") String name) throws ResponseStatusException {
		if (name != null) {
			try {
				List<Kid> all = service.getKidByName(name);
				return new ResponseEntity<List<Kid>>(all, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"El nino con nombre: " + name + "no se ha encontrado", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return un nino en concreto de la BBDD con el id pasado por parametro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Get a kid by id", notes = "Returns a kid as per the id")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,kid found"),
      @ApiResponse(code = 404, message = "ERROR:Kid not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Kid> getKidById(@PathVariable("id") Long id) throws ResponseStatusException {

		if (id != null && id > -1) {
			try {
				Kid kid = service.getKidById(id);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nino con id: " + id + "no se ha encontrado",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param n
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un nuevo nino creado en
	 *         la BBDD
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Create a new kid", notes = "Returns a new kid")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,kid can be created"),
      @ApiResponse(code = 404, message = "ERROR:Kid can't be created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@PostMapping
	public ResponseEntity<Kid> createKid(@RequestBody Kid n) throws ResponseStatusException {
		if (n != null) {
			try {
				Kid kid = service.createKid(n);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nino no ha sido guardado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
	}

	/**
	 * @param n
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un nino actualizado en
	 *         la BBDD
	 * @throws ResponseStatusException (nos devolveria unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Update a kid", notes = "Returns a updated kid")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,kid can be created"),
      @ApiResponse(code = 404, message = "ERROR:Kid can't be created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@PutMapping
	public ResponseEntity<Kid> UpdateKid(@RequestBody Kid n) throws ResponseStatusException {
		if (n != null && n.getId() > 0) {
			try {
				Kid kid = service.Update(n);
				return new ResponseEntity<Kid>(kid, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nino no ha sido actualizado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}

	/**
	 * @param id
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un nino eliminado en la
	 *         BBDD
	 * @throws ResponseStatusException (En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Delete a kid", notes = "Delete a kid")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,kid can be deleted"),
      @ApiResponse(code = 404, message = "ERROR:Kid can't be deleted"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
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
