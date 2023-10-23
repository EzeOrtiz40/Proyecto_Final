package com.fashion.web.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fashion.web.entidades.Imagen;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.servicios.ImagenServicio;
import com.fashion.web.servicios.PublicacionServicio;

@Controller
@RequestMapping("/publicacion")
public class publicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @Autowired
    private ImagenServicio imagenServicio;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/agregar")
    public String agregar(@RequestParam String titulo, @RequestParam String contenido, @RequestParam(required = false) MultipartFile archivo, ModelMap model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Imagen imagen;
        try {
            if (archivo.getContentType().equals("application/octet-stream")) {
                imagen = imagenServicio.guardarImagen(null);
            } else {
                imagen = imagenServicio.guardar(archivo);
            }
            publicacionServicio.agregar(titulo, contenido, imagen, usuario);

            model.put("exito", "Se subio la publicacion");
        } catch (Exception e) {
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println(e.getMessage());
        }
        return "redirect:/perfil";
    }

}
