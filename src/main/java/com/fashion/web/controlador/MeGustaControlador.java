package com.fashion.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.exceptiones.Exceptiones;
import com.fashion.web.servicios.MegustaServicio;
import com.fashion.web.servicios.PublicacionServicio;

@Controller
@RequestMapping("/likes")
public class MeGustaControlador {
    @Autowired 
    private UserDetailsService userDetailsServece;

    @Autowired
    private PublicacionServicio publicacionServicio;
    @Autowired
    private MegustaServicio megustaServicio;

    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/like/{id}")
    public String darLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails)throws Exceptiones{

        if(userDetails == null){
            return "redirect: /login";
        }
        try {
            Usuario usuario = (Usuario) userDetailsServece.loadUserByUsername(userDetails.getUsername());
            Publicacion publicacion = publicacionServicio.getById(id);
            
        if (usuario != null && publicacion != null) {
            megustaServicio.crearLike(publicacion, usuario);
        }
        } catch (Exception e) {
            throw new Exceptiones("no se pudo dar like");
        }
        
        System.out.println(id);
        return "/perfil";
    }

    
}
