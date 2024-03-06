package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorMedicos {
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/pagina_medico")
    public String paginaMedico(){
        return "pagina_medico";
    }
}
