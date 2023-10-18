package com.fashion.web.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fashion.web.entidades.Usuario;
import com.fashion.web.servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;
    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session){

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if(logueado.getRol().toString().equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }

        return "inicio";
    }

    @GetMapping("/perfil")
    public String perfil(){
        return "perfil";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap model) {
        if(error != null){
            model.put("error", "Usuario o contrasena invalidos!!");
        }
        return "login";
    }
    
     @GetMapping("/registrar")
     public String registrar() {
         return "usuario_form";
     }

    @GetMapping("/{id}/perfil")
    public String perfilUsuario(@PathVariable String email, ModelMap modelo) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario usuario = usuarioServicio.buscarPorEmail(email);

        if (!usuario.getEmail().equals(username)) {
            
            return "redirect: index";
        }

        modelo.addAttribute("nombre", usuario.getNombre());
        modelo.addAttribute("apellido", usuario.getApellido());
        modelo.addAttribute("email", usuario.getEmail());
        
        return "perfil_usuario"; // El nombre de la vista de perfil de usuario
    }

}