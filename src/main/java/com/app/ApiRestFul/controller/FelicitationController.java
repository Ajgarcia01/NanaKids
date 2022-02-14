package com.app.ApiRestFul.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.services.FelicitationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/felicitation")
public class FelicitationController {

	@Autowired
	FelicitationService service;
	// antes de tocar esto tener el servicio hecho

	// -----------------GET

	@ApiOperation(value = "Get all felicitations", notes = "Returns an felicitation list")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 500, message = "Internal Error ") })
	@GetMapping
	public ResponseEntity<List<Felicitation>> getAllFelicitations() {
		List<Felicitation> list = service.getAllFelicitations();
		return new ResponseEntity<List<Felicitation>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	// Get felicitation by ID
    @ApiOperation(value = "Get a felicitation by id", notes = "Returns a felicitation as per the id")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,felicitation found"),
      @ApiResponse(code = 404, message = "ERROR:Felicitation not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@GetMapping("/{id}")
	public ResponseEntity<Felicitation> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {

		Felicitation entity = service.getFelicitationById(id);

		return new ResponseEntity<Felicitation>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	// Get count felicitations
    @ApiOperation(value = "Count felicititation in the Database", notes = "Returns the total")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "ERROR:Cant count the felictations "),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
	@GetMapping("/count/")
	public Long numberFelicitations() {
		Long result = service.numberFelicitations();
		return result;
	}

	// Get ListFelicitation by type
	@GetMapping("/search/{type}")
	public ResponseEntity<List<Felicitation>> getListByType(@PathVariable("type") int type) {
		List<Felicitation> list = service.listTypeFelicitation(type);
		return new ResponseEntity<List<Felicitation>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	// Get felicitation by id

	// -----------------------------------POST

	/*
	 * @PostMapping public ResponseEntity<Felicitation>
	 * createOrUpdateItem( @RequestBody Felicitation felicitation) throws
	 * RecordNotFoundException { Felicitation updated =
	 * service.createOrUpdate(felicitation); return new
	 * ResponseEntity<Felicitation>(updated, new HttpHeaders(), HttpStatus.OK); }
	 * 
	 */

	// CAMBIAR ESTADO DE LA LISTA A ENVIADO
	/*
	 * DEVOLVIENDO UNA LISTA
	 * 
	 * @PostMapping public ResponseEntity<List<Felicitation>> updateStatus(){
	 * 
	 * List<Felicitation> list = service.getAllFelicitations(); List<Felicitation>
	 * ls = service.changeStatusFelicitation(list);
	 * 
	 * return new ResponseEntity<List<Felicitation>>(ls , new HttpHeaders() ,
	 * HttpStatus.OK);
	 * 
	 * }
	 */
	// CAMBIAR ESTADO DE LA LISTA A ENVIADO
	// DEVOLVIENDO UN HttpStatus
	@PostMapping
	public HttpStatus updateStatus() {
		List<Felicitation> list = service.getAllFelicitations();
		service.changeStatusFelicitation(list);
		return HttpStatus.OK;
	}

	// -----------------------DELETE
	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteFelicitation(id);
		return HttpStatus.OK;
	}

}
