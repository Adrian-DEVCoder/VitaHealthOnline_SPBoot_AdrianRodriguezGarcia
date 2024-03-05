package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorLogin {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "iniciar_sesion";
    }

    @PostMapping("/login")
    public String procesarLogin(){
        return "/";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }
    @PostMapping("/logout")
    public String procesarLogout(){
        return "iniciar_sesion";
    }

}
