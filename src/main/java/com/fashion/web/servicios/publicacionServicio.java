// package com.fashion.web.servicios;

// import java.time.LocalDate;

// import javax.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.fashion.web.entidades.Imagen;
// import com.fashion.web.entidades.Publicacion;
// import com.fashion.web.entidades.Usuario;
// import com.fashion.web.exceptiones.Exceptiones;
// import com.fashion.web.repositorio.PubliRepositorio;
// import com.fashion.web.repositorio.UsuarioRepositorio;

// import java.util.Optional;
// import java.util.List;
// import java.util.ArrayList;


// @Service
// public class publicacionServicio {

//     @Autowired
//     PubliRepositorio pr;

//     @Autowired
//     UsuarioRepositorio ur;


//     @Transactional
//     public void crearPublicacion(String titulo, String contenido, Imagen im_publicacion, List<Usuario> user_publicacion)
//             throws Exception {
//         validar(titulo, contenido, im_publicacion);
//         Publicacion publicacion = new Publicacion();
//         publicacion.setUser_publicacion(user_publicacion);
//         publicacion.setTitulo(titulo);
//         publicacion.setIm_publicacion(im_publicacion);
//         publicacion.setFechaPubli(LocalDate.now());
//         pr.save(publicacion);

//     }

//     @Transactional
//     public void modificarPublicacion(String titulo, String contenido, String id) {
//         Optional<Publicacion> respuesta = pr.findById(id);
//         if (respuesta.isPresent()) {
//             Publicacion publicacion = respuesta.get();
//             publicacion.setTitulo(titulo);
//             publicacion.setContenido(contenido);
//             pr.save(publicacion);
//         }
//     }

//     @Transactional
//     public void eliminarPublicacion(String id) {
//         Optional<Publicacion> respuesta = pr.findById(id);
//         if (respuesta.isPresent()) {
//             pr.deleteById(id);
//         }
//     }

//     public List<Publicacion> listarPublicaciones(){
//         List<Publicacion> publicaciones = new ArrayList<>();
//         publicaciones = pr.findAll();
//         return publicaciones;
//     }

//     public Publicacion getOne(String id) { 
//        return pr.buscarPorId(id);
//     }

//     private void validar(String titulo, String contenido, Imagen im_publicacion) throws Exceptiones {
//         if (titulo == null || titulo.isEmpty()) {
//             throw new Exceptiones("El título no puede ser nulo ni estar vacío");
//         }

//         if (contenido == null || contenido.isEmpty()) {
//             throw new Exceptiones("El contenido no puede ser nulo ni estar vacío");
//         }

//         if (im_publicacion == null) {
//             throw new Exceptiones("La imagen no puede estar vacía ni ser nula");
//         }

//     }

// }
