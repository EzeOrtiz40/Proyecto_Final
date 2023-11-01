package com.fashion.web.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion.web.entidades.Comentario;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.repositorio.ComentarioRepositorio;

@Service
public class ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Transactional
    public Comentario agregar(String texto, Publicacion publicacion, Usuario usuario) {
        Comentario comentario = new Comentario();

        comentario.setTexto(texto);
        comentario.setPublicacion(publicacion);
        comentario.setUsuario(usuario);

        return comentarioRepositorio.save(comentario);
    }

    public List<Comentario> listarPorPublicacion(Long id) {
        return comentarioRepositorio.buscarPorIdPublicacion(id);
    }

    public List<Comentario> listarTodos() {
        return comentarioRepositorio.findAll();
    }

}