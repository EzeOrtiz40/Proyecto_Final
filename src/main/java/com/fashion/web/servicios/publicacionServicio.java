package com.fashion.web.servicios;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.ImagenRepositorio;
import com.fashion.web.repositorio.PubliRepositorio;
import com.fashion.web.repositorio.UsuarioRepositorio;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


@Service
public class PublicacionServicio {

    @Autowired
    private PubliRepositorio publiRepositorio;

    @Transactional
    public Publicacion agregar(String titulo, String contenido, Imagen imagen, Usuario usuario) {
        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(titulo);
        publicacion.setContenido(contenido);
        publicacion.setImagenPublicacion(imagen);
        publicacion.setUsuario(usuario);

        return publiRepositorio.save(publicacion);
    }

    public List<Publicacion> listarPublicaciones() {
        return publiRepositorio.findAll();
    }

    public List<Publicacion> listarPorId(Long id) {
        return publiRepositorio.buscarPorIdUsuario(id);
    }

    public Publicacion getById(Long id) {
        Optional<Publicacion> respuesta = publiRepositorio.findById(id);
        return (respuesta.isPresent()) ? respuesta.get() : null;
    }
}
