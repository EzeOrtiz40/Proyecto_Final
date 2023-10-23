package com.fashion.web.servicios;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.web.Enumeraciones.RolCategoria;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.PubliRepositorio;
import com.fashion.web.repositorio.UsuarioRepositorio;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


@Service
public class PublicacionServicio {

    @Autowired
    PubliRepositorio pr;

    @Autowired
    UsuarioRepositorio ur;


    @Transactional
    public void crearPublicacion(String contenido, RolCategoria rolCategoria, Imagen im_publicacion, Long id_user_publicacion)
            throws Exception {

        validar(contenido, im_publicacion);
        Publicacion publicacion = new Publicacion();
        publicacion.setContenido(contenido);
        publicacion.setId_user_publicacion(id_user_publicacion);
        publicacion.setIm_publicacion(im_publicacion);
        publicacion.setFechaPubli(new Date());

        publicacion.setRolCategoria(rolCategoria);

        pr.save(publicacion);

    }

    @Transactional
    public void modificarPublicacion(String contenido, Long id) {
        Optional<Publicacion> respuesta = pr.findById(id);
        if (respuesta.isPresent()) {
            Publicacion publicacion = respuesta.get();
            publicacion.setContenido(contenido);
            pr.save(publicacion);
        }
    }

    @Transactional
    public void eliminarPublicacion(Long id) {
        Optional<Publicacion> respuesta = pr.findById(id);
        if (respuesta.isPresent()) {
            pr.deleteById(id);
        }
    }

    public List<Publicacion> listarPublicaciones(){
        List<Publicacion> publicaciones = new ArrayList<>();
        publicaciones = pr.findAll();
        return publicaciones;
    }

    public Publicacion buscarPorId(Long id) { 
       return pr.buscarPorId(id);
    }

    private void validar(String contenido, Imagen im_publicacion) throws Exceptiones {

        if (contenido == null || contenido.isEmpty()) {
            throw new Exceptiones("El contenido no puede ser nulo ni estar vacío");
        }

        if (im_publicacion == null) {
            throw new Exceptiones("La imagen no puede estar vacía ni ser nula");
        }

    }

}
