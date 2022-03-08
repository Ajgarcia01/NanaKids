package com.app.ApiRestFul.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.services.CloudinaryService;

@RestController
@RequestMapping("/Cloudinary")
public class CloudinaryController {

	@Autowired
	CloudinaryService service;
	//*
	//	--->		GET			<---
	//*
  ///  @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	/*
	@GetMapping("/foto/{urlImage}")
	public ResponseEntity<String> getUrlImageCloudinary(@PathVariable("urlImage") String urlImage ){
		try {
		String urlImageCloudinary  =  service.uploadPhoto(urlImage);
		System.out.println("ENTRE AL CONTROLLER "+urlImageCloudinary);
		return new ResponseEntity<String>(urlImageCloudinary , new HttpHeaders() , HttpStatus.OK);
		
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LA URL DEL CLOUDINATY ES "+urlImage, e);
		}
	}
	
	@GetMapping("/count/date-type/{file}")
	public ResponseEntity<String> UpdateFelicitation(@PathVariable("file")MultipartFile f ) throws ResponseStatusException {
		
			try {
				 String urlImageCloudinary  =  service.uploadPhoto(f);
					System.out.println("ENTRE AL CONTROLLER "+urlImageCloudinary);
					return new ResponseEntity<String>(urlImageCloudinary , new HttpHeaders() , HttpStatus.OK);			
					} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La felicitacion no ha sido actualizada correctamente",e);
			}
		
		

	}
	

	    //*
	    //    --->        GET            <---
	    //*
	  ///  @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	    @GetMapping("/foto/{urlImage}")
	    public ResponseEntity<String> getUrlImageCloudinary(@PathVariable("urlImage") File urlImage ){
	        try {
	        String urlImageCloudinary  =  service.uploadPhoto(urlImage);
	        return new ResponseEntity<String>(urlImageCloudinary , new HttpHeaders() , HttpStatus.OK);

	        } catch (ResponseStatusException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LA URL DEL CLOUDINATY ES "+urlImage, e);
	        }
	    }
*/
	}

