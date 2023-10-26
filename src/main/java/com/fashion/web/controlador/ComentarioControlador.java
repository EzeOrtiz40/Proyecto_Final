package com.fashion.web.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.servicios.ComentarioServicio;
import com.fashion.web.servicios.PublicacionServicio;

@Controller
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private PublicacionServicio publicacionServicio;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/agregar")
    public String agregarComentario(@RequestParam String comentario, @RequestParam Long publicacionid, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Publicacion publicacion = publicacionServicio.getById(publicacionid);
        try {
            comentarioServicio.agregar(comentario, publicacion, usuario);
        } catch (Exception e) {
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println("*");
            System.out.println(e.getMessage());
        }
        return "redirect:/publicacion/p/" + publicacionid;
    }
    
}
