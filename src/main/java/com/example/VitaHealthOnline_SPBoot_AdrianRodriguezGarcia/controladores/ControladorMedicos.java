package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.*;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControladorMedicos {

    @Autowired
    RepositorioPaciente repositorioPaciente;
    @Autowired
    RepositorioConsulta repositorioConsulta;
    @Autowired
    RepositorioDatosSalud repositorioDatosSalud;
    @Autowired
    RepositorioHistorial repositorioHistorial;
    @Autowired
    RepositorioDiagnostico repositorioDiagnostico;
    @Autowired
    Diagnostico diagnostico;
    @Autowired
    Historial historial;
    @Autowired
    DatosSalud datosSalud;
    @Autowired
    Consulta consulta;
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
    @GetMapping("/detalle_paciente")
    public String detallePaciente(@RequestParam("id") int idPaciente, Model model){
        if(idPaciente != 0){
            Paciente pacienteActual = repositorioPaciente.findById(idPaciente).orElse(null);
            List<Consulta> consultas = repositorioConsulta.findByPaciente(pacienteActual);
            List<DatosSalud> datosSalud = repositorioDatosSalud.findByPaciente(pacienteActual);
            Historial historial = repositorioHistorial.findHistorialByPaciente(pacienteActual);
            if(pacienteActual != null){
                model.addAttribute("paciente",pacienteActual);
                model.addAttribute("consultas",consultas);
                model.addAttribute("datosSalud",datosSalud);
                if(historial != null){
                    model.addAttribute("historial",historial);
                }
                return "detalle_paciente";
            }
        }
        return "redirect:/gestion_pacientes";
    }
    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/agregar_registro_historial")
    public String agregarRegistroHistorial(){
        return "agregar_registro_historial";
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/agregar_registro_historial")
    public String procesarAgregarRegistroHistorial(@RequestParam("idHistorial") String idHistorials,
                                                   @RequestParam("idPaciente") String idPaciente,
                                                   @RequestParam("diagnostico") String diagnostico,
                                                   @RequestParam("tratamiento") String tratamiento){
        if(!idHistorials.isEmpty()){
            int idHistorial = Integer.parseInt(idHistorials);
            Historial historial = repositorioHistorial.findById(idHistorial).orElse(null);
            if(historial != null){
                List<Diagnostico> diagnosticos = repositorioDiagnostico.findByHistorial(historial);
                Diagnostico nuevoDiagnostico = new Diagnostico();
                nuevoDiagnostico.setDiagnostico(diagnostico);
                nuevoDiagnostico.setTratamiento(tratamiento);
                repositorioDiagnostico.save(nuevoDiagnostico);
                diagnosticos.add(nuevoDiagnostico);
                historial.setDiagnosticos(diagnosticos);
                repositorioHistorial.save(historial)
            } else {
                // Implementar el caso si no tiene historial
            }
        }
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
