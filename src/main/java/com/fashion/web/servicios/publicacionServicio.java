package com.fashion.web.servicios;



import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.PubliRepositorio;


import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


@Service
public class PublicacionServicio {

    @Autowired
    private PubliRepositorio publiRepositorio;


    @Transactional
    public void crearPublicacion(String titulo, String contenido, Imagen im_publicacion)
            throws Exceptiones {  
                try {
                    validar(titulo, contenido, im_publicacion);
        
                                Publicacion publicacion = new Publicacion();
                                
                                publicacion.setTitulo(titulo);
                                publicacion.setContenido(contenido);
                                publicacion.setImagenPublicacion(im_publicacion);
                                publicacion.setFecha(new Date(System.currentTimeMillis()));
                                publiRepositorio.save(publicacion);

                } catch (Exceptiones e) {

                    throw new Exceptiones("No se pudo cargar la publicacion");
                }
                                
    }

    @Transactional
    public void modificarPublicacion(String titulo, String contenido, Long id) {
        Optional<Publicacion> respuesta = publiRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Publicacion publicacion = respuesta.get();
            publicacion.setTitulo(titulo);
            publicacion.setContenido(contenido);
            
            publiRepositorio.save(publicacion);
        }
    }

    @Transactional
    public void eliminarPublicacion(Long id) {
        Optional<Publicacion> respuesta = publiRepositorio.findById(id);
        if (respuesta.isPresent()) {
            publiRepositorio.deleteById(id);
        }
    }

    public List<Publicacion> listarPublicaciones(){
        List<Publicacion> publicaciones = new ArrayList<>();
        publicaciones = publiRepositorio.findAll();
        return publicaciones;
    }

    public Publicacion getOne(Long id) { 
       return publiRepositorio.buscarPorId(id);
    }

    private void validar(String titulo, String contenido, Imagen im_publicacion) throws Exceptiones {
        if (titulo == null || titulo.isEmpty()) {
            throw new Exceptiones("El título no puede ser nulo ni estar vacío");
        }

        if (contenido == null || contenido.isEmpty()) {
            throw new Exceptiones("El contenido no puede ser nulo ni estar vacío");
        }

        if (im_publicacion == null) {
            throw new Exceptiones("La imagen no puede estar vacía ni ser nula");
        }

    }

}
