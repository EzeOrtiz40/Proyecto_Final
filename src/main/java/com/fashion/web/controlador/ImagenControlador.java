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

import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.servicios.ImagenServicio;
import com.fashion.web.servicios.UsuarioServicio;


@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    ImagenServicio imagenServicio;


    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable Long id){

        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);

        byte[] imagen = usuario.getImagen().getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(imagen, headers, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtener(@PathVariable Long id){
        Imagen imagenData = imagenServicio.getOne(id);
        
        byte[] imagen = imagenData.getContenido();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(imagen, headers, HttpStatus.OK);
    }
}
