package com.fashion.web.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fashion.web.entidades.MeGusta;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.MegustaException;
import com.fashion.web.repositorio.MegustaRepositorio;

@Service
public class MegustaServicio {

    @Autowired
    private MegustaRepositorio megustaRepositorio;

    @Transactional
    public void crearLike(Publicacion publicacion, Usuario usuario) {
        try {
            if (megustaRepositorio.existsByUsuarioAndPublicacion(publicacion, usuario)) {
                megustaRepositorio.deleteByPublicacionAndUsuario(publicacion, usuario);
            } else {
                MeGusta likes = new MeGusta();
                likes.setPublicacion(publicacion);
                likes.setUsuario(usuario);
                megustaRepositorio.save(likes);
            }
        } catch (DataIntegrityViolationException e) {
            throw new MegustaException("No se pudo dar like: Ya diste like a esta publicación");
        } catch (Exception e) {
            throw new MegustaException("Error al dar like a la publicación");
        }
    }

    public long cantidadMeGusta(Publicacion publicacion) {

        return megustaRepositorio.countByPublicacion(publicacion);
    }

    // Valido si existe un like
    public boolean validarLike(Publicacion publicacion, Usuario usuario) {

        return megustaRepositorio.existsByUsuarioAndPublicacion(publicacion, usuario);
    }
}
