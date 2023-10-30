package com.fashion.web.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fashion.web.entidades.Publicacion;
import com.fashion.web.entidades.Usuario;
import com.fashion.web.servicios.UsuarioServicio;
import com.fashion.web.servicios.PublicacionServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    PublicacionServicio publicacionServicio;

    @GetMapping("/")
    public String Index(ModelMap model) {
        
        List<Publicacion> publicaciones = publicacionServicio.listarPublicaciones();
        model.put("publicaciones", publicaciones);
        model.put("css", "index.css");
        return "index";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap model){
        model.put("css", "index.css");
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if(logueado.getRol().toString().equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }

        return "inicio";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(HttpServletRequest request,ModelMap model){
        
        HttpSession session = request.getSession(false);

        if(session != null){
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            List<Publicacion> publicaciones = publicacionServicio.listarPorId(usuario.getId());

            if(usuario != null){
                
                model.addAttribute("nombre", usuario.getNombre());
                model.addAttribute("apellido", usuario.getApellido());
                model.addAttribute("email", usuario.getEmail());
                model.addAttribute("id", usuario.getId());
                model.addAttribute("fecha", usuario.getFecha_creacion());
                model.addAttribute("publicaciones", publicaciones);
            }
        }
        model.put("css", "index.css");
        return "perfil";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil/{id}")
    public String perfilById(@PathVariable("id") Long id , ModelMap model){
        
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
        List<Publicacion> publicaciones = publicacionServicio.listarPorId(usuario.getId());

        if(usuario != null){
                
            model.addAttribute("nombre", usuario.getNombre());
            model.addAttribute("apellido", usuario.getApellido());
            model.addAttribute("email", usuario.getEmail());
            model.addAttribute("id", usuario.getId());
            model.addAttribute("fecha", usuario.getFecha_creacion());
            model.addAttribute("publicaciones", publicaciones);
        }
        
        return "perfil";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
        if(error != null){
            model.put("error", "Usuario o contrasena invalidos!!");
        }
        model.put("css","login.css");
        return "login";
    }
    
     @GetMapping("/registrar")
     public String registrar() {
         return "usuario_form";
     }

     @GetMapping("/publicar")
     public String publicar() {
         return "publicar";
     }
}