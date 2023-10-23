package com.fashion.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashion.web.entidades.Publicacion;
import com.fashion.web.servicios.ImagenPubliServicio;
import com.fashion.web.servicios.PublicacionServicio;

@Controller
@RequestMapping("/imagenPublicacion")
public class ImagenPubliControlador {
    
    @Autowired
    PublicacionServicio publicacionServicio;

    @Autowired
    ImagenPubliServicio imagenPubliServicio;


    @GetMapping("/publicacion/{id}")
    public ResponseEntity<byte[]> imagenPubliPublicacion(@PathVariable Long id){

        Publicacion publicacion = publicacionServicio.buscarPorId(id);


        byte[] imagenPubli = publicacion.getIm_publicacion().getContenido(); // ojoooooo
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(imagenPubli, headers, HttpStatus.OK);

    }

    // @GetMapping("/{id}")
    // public ResponseEntity<byte[]> obtener(@PathVariable Long id){
    //     Imagen imagenData = imagenServicio.getOne(id);
        
    //     byte[] imagen = imagenData.getContenido();
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_JPEG);

    //     return new ResponseEntity<byte[]>(imagen, headers, HttpStatus.OK);
    // }
}
