package com.fashion.web.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminControlador {
    
    @GetMapping("/dasboard")
    public String dashboard(){
        return "panel";
    }
}

