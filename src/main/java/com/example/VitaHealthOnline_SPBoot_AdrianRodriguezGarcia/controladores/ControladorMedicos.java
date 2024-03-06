package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ControladorMedicos {

    @Autowired
    RepositorioPaciente repositorioPaciente;
    @Autowired
    Paciente paciente;

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/pagina_medico")
    public String paginaMedico(){
        return "pagina_medico";
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/gestion_pacientes")
    public String gestionPacientes(Model model){
        List<Paciente> pacientes = repositorioPaciente.findAll();
        if(!pacientes.isEmpty()){
            model.addAttribute("pacientes", pacientes);
        }
        return "gestion_pacientes";
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/gestion_citas")
    public String gestionCitas(Model model){
        return "gestion_citas";
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/analisis_datos")
    public String analisisDatos(Model model){
        return "analisis_datos";
    }
}