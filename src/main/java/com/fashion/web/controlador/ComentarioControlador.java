// package com.fashion.web.controlador;

// import java.util.Optional;

// import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import com.fashion.web.entidades.Comentario;
// import com.fashion.web.entidades.Publicacion;
// import com.fashion.web.entidades.Usuario;
// import com.fashion.web.exceptiones.Exceptiones;
// import com.fashion.web.servicios.ComentarioServicio;

// @Controller
// @RequestMapping("/usuario/comentario")
// public class ComentarioControlador {
    
//     @Autowired
//     ComentarioServicio comentarioServicio;
    
//     @GetMapping("/crear") // traigo la session para obtener los datos de usuario y publicacion
//     public Comentario agregarComentario(@RequestParam String texto,  HttpSession session) throws Exceptiones{
        
//         try {
//             //obtengo el id de la publicacion seteada en el metodo verPublicacion 
//             Publicacion publicacion = (Publicacion) session.getAttribute("publicacion");
//             //obtengo los datos del usuario
//             Usuario usuario = (Usuario) session.getAttribute("usuariosession");
//              Comentario comentario = comentarioServicio.guardarComentario(texto, usuario,publicacion);

//             return comentario;

//         } catch (Exception e) {

//             throw new Exceptiones("No se puede guardar el comentario");
//         }
//      }

//     @GetMapping("/buscar/{id}")
//     public Optional<Comentario> buscar(@PathVariable Long id){
//         return comentarioServicio.buscarbyId(id);
//     }

//     // @GetMapping("/eliminar/{id}")
//     // public Comentario eliminar(@PathVariable Long id){
//     //     return comentarioServicio.deleteById(id);
//     // }
// }
