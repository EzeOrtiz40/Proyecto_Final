package com.fashion.web.servicios;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fashion.web.entidades.Comentario;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.repositorio.ComentarioRepositorio;


@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    public Comentario guardarComentario(String texto, Usuario usuario,Publicacion publicacion) throws Exceptiones{

        Comentario comentario = new Comentario();

        if (texto.trim().isEmpty()) {

            throw new Exceptiones("El comentario no debe estar vacio");

        }else {

            comentario.setTexto(texto);
            comentario.setUsuario(usuario);
            comentario.setPublicacion(publicacion);
        }
        
        Comentario comentarioNuevo = comentarioRepositorio.save(comentario);

        return comentarioNuevo;
    }

    public void deleteById(Long id){
        comentarioRepositorio.deleteById(id);
    }

    public Comentario buscarbyId(Long id){
       Optional<Comentario> respuesta = comentarioRepositorio.findById(id);

       return (respuesta.isPresent())? respuesta.get(): null;
    }


}
