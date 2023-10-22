// package com.fashion.web.servicios;


// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.fashion.web.entidades.Comentario;
// import com.fashion.web.entidades.Publicacion;
// import com.fashion.web.entidades.Usuario;
// import com.fashion.web.exceptiones.Exceptiones;
// import com.fashion.web.repositorio.ComentarioRepositorio;
// import com.fashion.web.repositorio.PubliRepositorio;

// @Service
// public class ComentarioServicio {
//     @Autowired
//     ComentarioRepositorio comentarioRepositorio;
//     @Autowired
//     PubliRepositorio publiRepositorio;

//     public Comentario guardarComentario(String texto, Usuario usuario,Publicacion publicacion) throws Exceptiones{

//         Comentario comentario = new Comentario();

//         if (texto.trim().isEmpty()) {

//             throw new Exceptiones("El comentario no debe estar vacio");

//         }else {
            
//             comentario.setUsuario(usuario);
//             comentario.setTexto(texto);
//             comentario.setPublicacion(publicacion);
//         }
//         Comentario comentarioNuevo = comentarioRepositorio.save(comentario);
//         return comentarioNuevo;
//     }

//     // public Comentario deleteById(Long id){
        
//     //     return comentarioRepositorio.buscarbyId(id);
//     // }

//     public Optional<Comentario> buscarbyId(Long id){
//        return  comentarioRepositorio.findById(id);
//     }
// }
