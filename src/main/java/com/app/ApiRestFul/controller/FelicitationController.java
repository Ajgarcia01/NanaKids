package com.app.ApiRestFul.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.services.CloudinaryService;
import com.app.ApiRestFul.services.FelicitationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/*
 * @author= Gonzalo Bretones-Mora Quero 
 */

@RestController
@RequestMapping("/felicitation")
public class FelicitationController {

	

	@Autowired
	FelicitationService service;
	CloudinaryService serviceCloud;
	
	
			//*
			//		--->		GET			<---
			//*
	
	
	/**
	 * --> OBTENER TODAS LAS FELICITACIONES	<--
	 * 
	 * 
	 * @return EndPoint que nos devuelve un HttpStatus.OK y Lista de todas las felicitaciones de la base de datos.
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND)
	 */
    @ApiOperation(value = "Get all felicitation", notes = "Returns a felicitation list")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "ERROR:Can't get all felicitation"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping
	public ResponseEntity<List<Felicitation>> getAllFelicitations() {
		try {
		List<Felicitation> list = service.getAllFelicitations();
		return new ResponseEntity<List<Felicitation>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay felicitaciones",e);
		}
	}
	
	
	/**
	 * -->OBTENER FELICITACION POR ID	<--
	 * 
	 * @param id
	 * @return EndPoint que nos devuelve un HttpStatus.OK y Felicitacion de la base de datos con el id pasado como parametro.
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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
	
	
	
	/**
	 * --> OBTENER LISTA DE FELICITACIONES POR TIPO	<--
	 * 
	 * @param type Tipo felicitacion
	 * @return EndPoint que nos devuelve un HttpStatus.OK y Lista de felicitaciones de la base de datos,
	 * con el tipo pasado como parametro.
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
    @ApiOperation(value = "Get all felicitation by type", notes = "Returns all felicitation as per the type")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,felicitations found"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping("/search/{type}")
	public ResponseEntity<List<Felicitation>> getListByType( @PathVariable("type")int type){
		try {
			
		List<Felicitation> list  =  service.listTypeFelicitation(type);
		return new ResponseEntity<List<Felicitation>>(list , new HttpHeaders() , HttpStatus.OK);
		
		} catch (ResponseStatusException e) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Las Felicitaciones con tipo: " + type + "no se ha encontrado", e);
		}
	}
	
	
	/**
	 * --> OBTENER LISTA DE TODAS LAS FELICITACIONES CON FECHA DE HOY Y DEL TIPO x	<--
	 * 
	 * @param type Tipo felicitacion
	 * @return EndPoint que nos devuelve un HttpStatus.OK y Lista de felicitaciones de la base de datos,
	 * con el tipo pasado como parametro y la fecha actual (dia y mes).
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND)
	 */
    @ApiOperation(value = "Get all felicitation by type and date", notes = "Returns all felicitation as per the type and date")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Felicitations found"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping("/search/date-type/{type}")
	public ResponseEntity<List<Felicitation>> getlistFelicitationByDateAndType(@PathVariable("type")int type ){
		try {
		List<Felicitation> list  =  service.listFelicitationByDateAndType(LocalDate.now(), type);
		return new ResponseEntity<List<Felicitation>>(list , new HttpHeaders() , HttpStatus.OK);
		
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Las Felicitaciones con tipo: " + type +" y en con fecha "+LocalDate.now()+ " no se ha encontrado", e);
		}
	}
	
	
	/**
	 * --> OBTENER EL NUMERO DE FELICITACIONES TOTALES	<--
	 * 
	 * 
	 * @return EndPoint que nos devuelve un HttpStatus.OK y Numero de felicitaciones de la base de datos.
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND)
	 */
	
    @ApiOperation(value = "Get total felicitations in the BBDD", notes = "Returns the total felicitations ")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Felicitations found"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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
	
	
	/**
	 * --> OBTENER NUMERO DE FELICITACIONES CON FECHA ACTUAL Y TIPO x	<--
	 * 
	 * @param type Tipo felicitacion
	 * @return EndPoint que nos devuelve un HttpStatus.OK y Numero de felicitaciones de la base de datos, con la fecha actual (dia y mes)
	 *  y el tipo pasado como parametro.
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND)
	 */
    @ApiOperation(value = "Get total felicitations in the BBDD", notes = "Returns the total felicitations for Type and date ")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Felicitations found"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@GetMapping("/count/date-type/{type}")
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
	
	
	
	/**
	 * --> AÑADIR FELICITACION	<--
	 * 
	 * @param f Felicitacion , fotoNube String corresponde a la url de la foto guardada en cloudinary
	 * @return EndPoint que nos devuelve un HttpStatus.OK y una nueva felicitacion creado en
	 *         la BBDD
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
    @ApiOperation(value = "Create felicitation", notes = "Returns the new felicitation ")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Felicitations created"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not create"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    @RequestMapping(path = "", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@PostMapping
	public ResponseEntity<Felicitation> createFelicitation(  @RequestPart Felicitation f , @RequestPart MultipartFile photo) throws ResponseStatusException {
		if (f != null) {
			try {								
				f.setImage(CloudinaryService.uploadPhoto(photo));
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
	
	
	
	/**
	 * --> ACTUALIZAR FELICITACION	<--
	 * 
	 * @param f Felicitacion
	 * @return EndPoint que nos devuelve un HttpStatus.OK y una felicitacion actualizada en
	 *         la BBDD@RequestBody
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
    @ApiOperation(value = "Update felicitation", notes = "Returns the felicitation updated")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Felicitations updated"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not update"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@PutMapping
	public ResponseEntity<Felicitation> UpdateFelicitation(@RequestPart Felicitation f , @RequestPart MultipartFile photo) throws ResponseStatusException {
		if (f != null && f.getId() > 0) {
			try {
				f.setImage(CloudinaryService.uploadPhoto(photo));
				Felicitation felicitation = service.UpdateFelicitation(f);
				return new ResponseEntity<Felicitation>(felicitation, new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido actualizada correctamente",e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

	}
	

	/**
	 * --> CAMBIO DE ESTADO DE FELICITACION = ENVIADO	<--
	 * 
	 * @param type Tipo felicitacion
	 * @return EndPoint que nos devuelve un HttpStatus.OK
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND)
	 */
    @ApiOperation(value = "Change the atribute(type) of the felicitation", notes = "Returns the new felicitation ")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Atribute type change"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not update"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@PutMapping("/change-to-sent/{type}")
	public HttpStatus changeToSentFelicitation(@PathVariable("type")int type) throws ResponseStatusException {
		
			try {
				service.changeToSent(type, LocalDate.now());
				return HttpStatus.OK;
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido actualizada correctamente",e);
			}
		
	}
	
	
	/**
	 * --> CAMBIO DE ESTADO DE FELICITACION = NO ENVIADO	<--
	 * 
	 * @param type Tipo felicitacion
	 * @return EndPoint que nos devuelve un HttpStatus.OK
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND)
	 */
    @ApiOperation(value = "Change the atribute(type) of the felicitation", notes = "Returns the felicitation updated")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Atribute type change"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not update"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@PutMapping("/change-to-unsent/{type}")
	public HttpStatus changeToUnentFelicitation(@PathVariable("type")int type) throws ResponseStatusException {
		
			try {
				service.changeToUnsent(type, LocalDate.now());
				return HttpStatus.OK;
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido actualizada correctamente",e);
			}
		
	}
			//*
			//	--->		DELETE			<---
			//*
	
	
	//	-->	BORRAR FELICITACION POR ID	<--
	/**
	 * --> BORRAR FELICITACION POR ID	<--
	 * 
	 * @param id Identificador
	 * @return EndPoint que nos devuelve un HttpStatus.OK
	 * 
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en función de la
	 *                                 petición)
	 */
	
    @ApiOperation(value = "Delete felicitation", notes = "Delete felicitation")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,felicitation deleted"),
      @ApiResponse(code = 404, message = "ERROR:Felicitations not delete"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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
