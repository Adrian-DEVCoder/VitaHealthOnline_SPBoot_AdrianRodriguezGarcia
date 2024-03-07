package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.*;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    RepositorioMedico repositorioMedico;
    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    Medico medico;
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
    public String paginaMedico(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String nombreUsuario = userDetails.getUsername();
        Usuario usuarioActual = repositorioUsuario.findUsuarioByNombre(nombreUsuario);
        if(usuarioActual != null) {
            Medico medico = repositorioMedico.findMedicoByUsuario(usuarioActual);
            if(medico != null){
                int idMedico = medico.getId_profesional();
                if (idMedico != 0) {
                    return "pagina_medico";
                } else {
                    return "no_medico_registrado";
                }
            } else {
                return "no_medico_registrado";
            }
        } else {
            return "redirect:/";
        }
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/registro_medico")
    public String registroMedico(Model model){
        model.addAttribute("nuevomedico", medico);
        return "registro_medico";
    }

    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/insertar_medico")
    public String procesarInsertarMedico(@ModelAttribute("nuevomedico") Medico medico,
                                         @AuthenticationPrincipal UserDetails userDetails){
        String nombreUsuario = userDetails.getUsername();
        Usuario usuarioActual = repositorioUsuario.findUsuarioByNombre(nombreUsuario);
        if(usuarioActual != null){
            Medico nuevoMedico = new Medico();
            nuevoMedico.setNombre_profesional(medico.getNombre_profesional());
            nuevoMedico.setApellidos_profesional(medico.getApellidos_profesional());
            nuevoMedico.setEspecialidad(medico.getEspecialidad());
            nuevoMedico.setCorreo_electronico(medico.getCorreo_electronico());
            nuevoMedico.setTelefono(medico.getTelefono());
            nuevoMedico.setUsuario(usuarioActual);
            repositorioMedico.save(nuevoMedico);
            return "pagina_medico";
        } else {
            return "redirect:/registro_medico";
        }
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
    public String agregarRegistroHistorial(@RequestParam("id") int idPaciente, Model model){
        Paciente paciente = repositorioPaciente.findById(idPaciente).orElse(null);
        Historial historial = repositorioHistorial.findHistorialByPaciente(paciente);
        if(historial != null){
            model.addAttribute("historial", historial);
            model.addAttribute("paciente", paciente);
            return "agregar_registro_historial";
        } else {
            // Redirige al usuario a la página "no_historial" para crear un nuevo historial
            return "redirect:/no_historial?id=" + idPaciente;
        }
    }


    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/agregar_registro_historial")
    public String procesarAgregarRegistroHistorial(@RequestParam("idHistorial") int idHistorial,
                                                   @RequestParam("idPaciente") String idPaciente,
                                                   @RequestParam("diagnostico") String diagnostico,
                                                   @RequestParam("tratamiento") String tratamiento){
        if(idHistorial != 0){
            Historial historial = repositorioHistorial.findById(idHistorial).orElse(null);
            if(historial != null){
                Diagnostico nuevoDiagnostico = new Diagnostico();
                nuevoDiagnostico.setDiagnostico(diagnostico);
                nuevoDiagnostico.setTratamiento(tratamiento);
                nuevoDiagnostico.setHistorial(historial);
                repositorioDiagnostico.save(nuevoDiagnostico);
                return "redirect:/detalle_paciente?id="+idPaciente;
            } else {
                return "redirect:/gestion_pacientes";
            }
        } else {
            return "redirect:/no_historial?id="+idPaciente;
        }
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/no_historial")
    public String noHistorial(@RequestParam("id") int idPaciente, Model model){
        Paciente paciente = repositorioPaciente.findById(idPaciente).orElse(null);
        if(paciente != null){
            model.addAttribute("paciente", paciente);
            return "no_historial";
        } else {
            return "redirect:/gestion_pacientes";
        }
    }

    @PreAuthorize("hasRole('MEDICO')")
    @GetMapping("/crear_historial")
    public String crearHistorial(Model model){
        List<Medico> medicos = repositorioMedico.findAll();
        List<Paciente> pacientes = repositorioPaciente.findAll();
        model.addAttribute("medicos", medicos);
        model.addAttribute("pacientes", pacientes);
        return "crear_historial";
    }

    @PreAuthorize("hasRole('MEDICO')")
    @PostMapping("/agregar_historial")
    public String agregarHistorial(@RequestParam("idPaciente") int idPaciente,
                                   @RequestParam("medico") int idMedico){
        try{
            Medico medico = repositorioMedico.findById(idMedico).orElse(null);
            if(medico != null){
                Historial nuevoHistorial = new Historial();
                nuevoHistorial.setMedico(medico);
                Paciente paciente = repositorioPaciente.findById(idPaciente).orElse(null);
                nuevoHistorial.setPaciente(paciente);
                nuevoHistorial.setFecha_registro(new Date());
                return "redirect:/detalle_paciente?id="+idPaciente;
            } else {
                return "redirect:/gestion_pacientes";
            }
        } catch (Exception e){
            return "redirect:/gestion_pacientes";
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
