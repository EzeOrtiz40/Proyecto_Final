// package com.fashion.web.controlador;

// import java.util.List;
// import javax.servlet.http.HttpSession;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import com.fashion.web.entidades.Imagen;
// import com.fashion.web.entidades.Publicacion;
// import com.fashion.web.servicios.PublicacionServicio;

// @Controller
// public class PublicacionControlador {

//     @Autowired
//      PublicacionServicio publicacionServicio;

//     @GetMapping("/generar")
//     public String publicacion() {
//         return "generar_noticia.html";
//     }

//     @PostMapping("/generado")
//     public String subida(@RequestParam String titulo, @RequestParam String contenido, Imagen im_publicacion,
//             ModelMap modelo)
//        {

//         try {

//             publicacionServicio.crearPublicacion(titulo, contenido, im_publicacion);

//             modelo.put("Éxito", "La publicación fué subida con éxito");

//         } catch (Exception e) {

//             modelo.put("Error", "La publicación no pudo ser subida");

//             return "generar_noticia.html";
//         }
//         return "generar_noticia.html";
//     }

//     @GetMapping("/ultimaspublicaciones")
//     public String ultimas(ModelMap modelo){
//         List <Publicacion> publicaciones = publicacionServicio.listarPublicaciones();
//         modelo.put("publicaciones", publicaciones);
//         return "ultimasPublicaciones.html";
//     }

//     @GetMapping("/eliminar")
//     public String eliminar(@PathVariable Long id, ModelMap modelo){
//         publicacionServicio.eliminarPublicacion(id);
//         return null;
//         /*return "redirect:..(encargate frontman)" PUSE NULL PARA QUE NO SALTE NINGUN AVISO*/
//     }

//     @GetMapping("/verPublicacion")
//     public String verPublicacion(@PathVariable Long id, HttpSession session,ModelMap modelo) {
//         Publicacion publicacion = publicacionServicio.getOne(id);
//         session.setAttribute("publicacion", publicacion);
//         modelo.addAttribute("publicacion", publicacion);
//         return "verPublicacion.html";
//     }
// }
