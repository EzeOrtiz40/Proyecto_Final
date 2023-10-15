package com.fashion.web.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fashion.web.entidades.Imagen;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.ImagenRepositorio;

@Service
public class ImagenServicio {
    
    @Autowired
    private ImagenRepositorio imagenRepositorio;

    public Imagen guardar(MultipartFile archivo) throws Exceptiones{

        if(archivo != null){
            try {
                Imagen imagen = new Imagen();

                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

               return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getLocalizedMessage()+ "Puede que se haya un problema en el autogenerado de la imagen");
            }

        }
        return new Imagen();
    }

    public Imagen actualizar(MultipartFile archivo, String id) throws Exceptiones{

        if(archivo != null){
            try {
                Imagen imagen = new Imagen();

                if(id != null){
                    Optional<Imagen> respuesta = imagenRepositorio.findById(id);

                    if(respuesta.isPresent()){
                        imagen = respuesta.get();
                    }
                }
                imagen.setMime(archivo.getContentType());
                imagen.setNombre(archivo.getName());
                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
        return null;

        
    }

    public Imagen getOne(Long id) {
        return imagenRepositorio.getImagen(id);
    }
}
