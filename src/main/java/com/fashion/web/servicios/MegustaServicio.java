package com.fashion.web.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fashion.web.entidades.MeGusta;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.MegustaRepositorio;

@Service
public class MegustaServicio {

    @Autowired
    private MegustaRepositorio megustaRepositorio;
    @Transactional
    public void crearLike(Publicacion publicacion, Usuario usuario) throws Exceptiones{

        try {
            MeGusta like = megustaRepositorio.existsByUsuarioAndPublicacion(publicacion, usuario);
            // si me trae un like, lo elimino.
            if (like != null) {

                megustaRepositorio.delete(like);

            }else{

                MeGusta likes = new MeGusta();
                likes.setPublicacion(publicacion);
                likes.setUsuario(usuario);
                megustaRepositorio.save(likes);

            }
        } catch (Exception e) {
            throw new Exceptiones("No se guardo el like");
        }
     }

     public long cantidadMeGusta(Publicacion publicacion){

        return megustaRepositorio.countByPublicacion(publicacion);
     }

     //Valido si existe un like
     public MeGusta validarLike(Publicacion publicacion, Usuario usuario){
       
        return megustaRepositorio.existsByUsuarioAndPublicacion(publicacion, usuario);
    }
}
