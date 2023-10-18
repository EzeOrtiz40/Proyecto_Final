package com.fashion.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fashion.web.entidades.Imagen;
import java.util.List;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;

import com.fashion.web.servicios.publicacionServicio;

@Controller
@RequestMapping("/")
public class publicacionControlador {

    @Autowired
    private publicacionServicio ps;

    @GetMapping("/generar")
    public String publicacion() {
        return "generar_noticia.html";
    }

    @PostMapping("/generado")
    public String subida(@RequestParam String titulo, @RequestParam String contenido, Imagen im_publicacion,
            ModelMap modelo, Usuario user_publicacion) {
        try {
            ps.crearPublicacion(titulo, contenido, null, user_publicacion);
            modelo.put("Éxito", "La publicación fué subida con éxito");
        } catch (Exception e) {
            modelo.put("Error", "La publicación no pudo ser subida");
            return "generar_noticia.html";
        }
        return "generar_noticia.html";
    }

    @GetMapping("/ultimaspublicaciones")
    public String ultimas(ModelMap modelo){
        List <Publicacion> publicaciones = ps.listarPublicaciones();
        modelo.put("publicaciones", publicaciones);
        return "ultimasPublicaciones.html";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
        ps.eliminarPublicacion(id);
        return null;
        /*return "redirect:..(encargate frontman)" PUSE NULL PARA QUE NO SALTE NINGUN AVISO*/
    }

    @GetMapping("/verPublicacion/{id}")
    public String verPublicacion(@PathVariable String id, ModelMap modelo) {
        Publicacion publicacion = ps.getOne(id);
        modelo.addAttribute("publicacion", publicacion);
        return "verPublicacion.html";
    }


}
