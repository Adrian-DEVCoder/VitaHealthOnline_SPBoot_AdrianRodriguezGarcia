package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorAutorizacion {

    @GetMapping("/login")
    public String index(){
        return "iniciar_sesion";
    }
    @PostMapping("/login")
    String procesarLogin() {
        return "/";
    }

    @GetMapping("/registro")
    public String register(){
        return "registro";
    }
}
