package com.fashion.web.servicios;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.MegustaException;
import com.fashion.web.repositorio.PubliRepositorio;
import java.util.Optional;
import java.util.Date;
import java.util.List;

@Service
public class PublicacionServicio {

    @Autowired
    private PubliRepositorio publiRepositorio;

    @Transactional
    public void crearPublicacion(String titulo, String contenido, Imagen im_publicacion, Usuario usuario)
            throws MegustaException {

        validar(titulo, contenido, im_publicacion);

        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setImagenPublicacion(im_publicacion);
        publicacion.setFecha(new Date());
        publicacion.setUsuario(usuario);
        publiRepositorio.save(publicacion);
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

    public List<Publicacion> listarPublicaciones() {
        return publiRepositorio.findAll();
    }

    public Publicacion getOne(Long id) {
        return publiRepositorio.buscarPorId(id);
    }

    private void validar(String titulo, String contenido, Imagen im_publicacion) throws MegustaException {
        if (titulo == null || titulo.isEmpty()) {
            throw new MegustaException("El título no puede ser nulo ni estar vacío");
        }

        if (contenido == null || contenido.isEmpty()) {
            throw new MegustaException("El contenido no puede ser nulo ni estar vacío");
        }

        if (im_publicacion == null) {
            throw new MegustaException("La imagen no puede estar vacía ni ser nula");
        }

    }

    public List<Publicacion> listarPorId(Long id) {
        return publiRepositorio.buscarPorIdUsuario(id);
    }

    public Publicacion getById(Long id) {
        Optional<Publicacion> respuesta = publiRepositorio.findById(id);
        return (respuesta.isPresent()) ? respuesta.get() : null;
    }

}
