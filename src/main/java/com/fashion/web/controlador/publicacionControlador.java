package com.fashion.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fashion.web.Enumeraciones.RolCategoria;
import com.fashion.web.entidades.Imagen;
import java.util.List;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.servicios.ImagenServicio;
import com.fashion.web.servicios.PublicacionServicio;

@Controller
@RequestMapping("/")
public class publicacionControlador {

    @Autowired
    private PublicacionServicio ps;

    @Autowired
    private ImagenServicio imagenServicio;

    // @GetMapping("/generar")
    // public String publicacion() {
    //     return "generar_noticia.html";
    // }

    @PostMapping("/publicacion")
    public String subida(@RequestParam String contenido, @RequestParam RolCategoria rolCategoria,
            @RequestParam MultipartFile archivo,
            ModelMap modelo, Long id_user_publicacion) throws Exceptiones {

            System.out.println("EsTOY ENTRANDO A LA RUTA PUCLICACION"); 
            System.out.println(contenido);  
            System.out.println(id_user_publicacion); 

            Imagen imagen = null;    
            imagen = imagenServicio.guardar(archivo); 
                
        try {
            ps.crearPublicacion(contenido, rolCategoria, imagen, id_user_publicacion);
            modelo.put("Éxito", "La publicación fué subida con éxito");
        } catch (Exception e) {
            modelo.put("Error", "La publicación no pudo ser subida");
            return "inicio";
        }
        return "inicio";
    }

    @GetMapping("/mostrarPublicacion")
    public String mostrarPublicaciones(ModelMap modelo){
        List <Publicacion> publicaciones = ps.listarPublicaciones();

        System.out.println("ESTOY EN EL METODO MOSTRAR PUBLICACION");

        for (Publicacion publicacion : publicaciones) {
            System.out.println(publicacion.toString());
        }

        modelo.addAttribute("publicaciones", publicaciones);
        return "inicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo){
        ps.eliminarPublicacion(null);
        return null;
        /*return "redirect:..(encargate frontman)" PUSE NULL PARA QUE NO SALTE NINGUN AVISO*/
    }

    @GetMapping("/verPublicacion/{id}")
    public String verPublicacion(@PathVariable Long id, ModelMap modelo) {
        Publicacion publicacion = ps.buscarPorId(id);
        modelo.addAttribute("publicacion", publicacion);
        return "verPublicacion.html";
    }


}
