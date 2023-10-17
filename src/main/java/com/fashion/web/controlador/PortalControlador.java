package com.fashion.web.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @GetMapping("/inicio")
    public String inicio(){
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
}