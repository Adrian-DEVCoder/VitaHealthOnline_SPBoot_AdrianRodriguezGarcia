package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPacientes {
    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/pagina_paciente")
    public String paginaPaciente(){
        return "pagina_paciente";
    }


}
