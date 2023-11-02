package com.fashion.web.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fashion.web.entidades.Usuario;
import com.fashion.web.repositorio.UsuarioRepositorio;
import com.fashion.web.servicios.UsuarioServicio;

@Controller
@RequestMapping("/admin")
public class adminControlador {

    @Autowired
    UsuarioRepositorio ur;

    @Autowired    
    UsuarioControlador uc;

    @Autowired
    UsuarioServicio us;
    
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model){
        
        List<Usuario> usuarios = us.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "inicioAdmin"; 
    }

    // @GetMapping("/usuarios")
    // public String mostrarUsuarios(Model model) {
    //  // Devuelve el nombre de la vista que mostrará la lista de usuarios
    // }


}

