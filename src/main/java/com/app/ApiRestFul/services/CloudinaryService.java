package com.app.ApiRestFul.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
	
	private static final Logger log4 = LoggerFactory.getLogger(CloudinaryService.class);

	/*
	 * -->	SUBIR LAS FOTOS A CLOUDINARY	<--	
	 * 
	 * 
	 * @return Devuelve una url que corresponde a la foto guardada en cloudinary
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 
	
	public String  uploadPhoto(MultipartFile file ) {
		
		String result = "";
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dcbl6rgf5",
				"api_key", "996128877648275",
				"api_secret", "-f1WFF0cg7ukbi1Dp7n8QEKbGOU",
				"secure", true));
		
		Map uploadResult;
		
		try {
			uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			result = (String) uploadResult.get("url");
			System.out.println(result);
			log4.info("Imagen subida con exito al cloudinary");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log4.info("Error en la subida de la imagen");
			throw new IllegalArgumentException(
					"Valor introducido no valido" + "IllegalArgumentException: " + e);
		}
*/
	public String uploadPhoto(String foto) {

        String result = "";

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcbl6rgf5",
                "api_key", "996128877648275",
                "api_secret", "-f1WFF0cg7ukbi1Dp7n8QEKbGOU",
                "secure", true));

      //  File file = new File(foto);
        Map uploadResult;
        try {
            uploadResult = cloudinary.uploader().upload(foto, ObjectUtils.emptyMap());
            result = uploadResult.get("url").toString();
            System.out.println(result);
            log4.info("Imagen subida con exito al cloudinary");

            return result;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log4.info("Error en la subida de la imagen");
            throw new IllegalArgumentException(
                    "Valor introducido no valido" + "IllegalArgumentException: " + e);
        }
		
		/*
		
		File file = new File(foto);
		Map uploadResult;
		try {
			uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			result = (String) uploadResult.get("url");
			System.out.println(result);
			log4.info("Imagen subida con exito al cloudinary");
			
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log4.info("Error en la subida de la imagen");
			throw new IllegalArgumentException(
					"Valor introducido no valido" + "IllegalArgumentException: " + e);
		}
		*/
		
		
		
	}
}
