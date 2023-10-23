package com.fashion.web.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fashion.web.entidades.ImagenPubli;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.ImagenPubliRepositorio;

@Service
public class ImagenPubliServicio {
    
    @Autowired
    private ImagenPubliRepositorio imagenPubliRepositorio;

    public ImagenPubli guardar(MultipartFile archivo) throws Exceptiones{

        if(archivo != null){
            try {
                ImagenPubli imagenPubli = new ImagenPubli();

                imagenPubli.setMime(archivo.getContentType());
                imagenPubli.setNombre(archivo.getName());
                imagenPubli.setContenido(archivo.getBytes());

               return imagenPubliRepositorio.save(imagenPubli);

            } catch (Exception e) {
                System.err.println(e.getLocalizedMessage()+ "Puede que se haya un problema en el autogenerado de la imagenPubli");
            }

        }
        return new ImagenPubli();
    }

    public ImagenPubli guardarImagen(ImagenPubli imagen) {
        return imagenPubliRepositorio.save(imagen);
    }

    public ImagenPubli actualizar(MultipartFile archivo, String id) throws Exceptiones{

        if(archivo != null){
            try {
                ImagenPubli imagen = new ImagenPubli();

                if(id != null){
                    Optional<ImagenPubli> respuesta = imagenPubliRepositorio.findById(id);

                    if(respuesta.isPresent()){
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenPubliRepositorio.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
        return null;

        
    }

    public ImagenPubli getOne(Long id) {
        return imagenPubliRepositorio.getImagenPubli(id);
    }
}
