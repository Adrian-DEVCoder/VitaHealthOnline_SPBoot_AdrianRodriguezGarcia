package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorUsuarios {
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/pagina_medico")
    public String paginaMedico(){
        return "pagina_medico";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/pagina_paciente")
    public String paginaPaciente(){
        return "pagina_paciente";
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.getAuthorities() != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_MEDICO")) {
                    return "redirect:/pagina_medico";
                } else if (authority.getAuthority().equals("ROLE_PACIENTE")) {
                    return "redirect:/pagina_paciente";
                }
            }
        }
        return "redirect:/login";
    }
}
