package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Usuario;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioPaciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class ControladorPacientes {

    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    RepositorioPaciente repositorioPaciente;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    Usuario usuario;
    @Autowired
    Paciente paciente;

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/pagina_paciente")
    public String paginaPaciente(){
        return "pagina_paciente";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/perfil")
    public String perfilPaciente(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        Usuario usuario = repositorioUsuario.findUsuarioByNombre(username);
        if (usuario != null && usuario.getRol().equalsIgnoreCase("PACIENTE")) {
            int idUsuario = usuario.getId_usuario();
            if (idUsuario != 0) {
                Paciente paciente = repositorioPaciente.findPacienteByUsuario(usuario);
                if (paciente != null) {
                    model.addAttribute("paciente", paciente);
                    model.addAttribute("usuario", usuario);
                    return "perfil_paciente";
                } else {
                    return "redirect:/no_registrado";
                }
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/no_registrado")
    public String noRegistrado(){
        return "no_registrado";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/registro_paciente")
    public String registroPaciente(Model model){
        model.addAttribute("nuevopaciente", paciente);
        return "registro_paciente";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @PostMapping("/insertar_paciente")
    public String insertarPaciente(@ModelAttribute("nuevopaciente") Paciente paciente,
                                   @AuthenticationPrincipal UserDetails userDetails) throws ParseException {
        String username = userDetails.getUsername();
        Usuario usuarioActual = repositorioUsuario.findUsuarioByNombre(username);
        if(usuarioActual != null && usuarioActual.getRol().equalsIgnoreCase("PACIENTE")){
            Paciente nuevoPaciente = new Paciente();
            nuevoPaciente.setNombre_paciente(paciente.getNombre_paciente());
            nuevoPaciente.setApellidos_paciente(paciente.getApellidos_paciente());
            nuevoPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
            nuevoPaciente.setSexo(paciente.getSexo());
            nuevoPaciente.setDireccion(paciente.getDireccion());
            nuevoPaciente.setCorreo_electronico(paciente.getCorreo_electronico());
            nuevoPaciente.setTelefono(paciente.getTelefono());
            nuevoPaciente.setUsuario(usuarioActual);
            repositorioPaciente.save(nuevoPaciente);
            return "redirect:/perfil";
        } else {
            return "redirect:/no_registrado";
        }
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/cambiar_contrasena")
    public String cambiarContrasena(){
        return "cambiar_contrasena";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @PostMapping("/cambiar_contrasena")
    public String procesarCambioContrasena(@RequestParam("contrasenaActual") String contrasenaActual,
                                           @RequestParam("nuevaContrasena") String nuevaContrasena,
                                           @RequestParam("confirmacionContrasena") String confirmacionContrasena,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Usuario usuarioActual = repositorioUsuario.findUsuarioByNombre(username);
        if (passwordEncoder.matches(contrasenaActual, usuarioActual.getContrasena())) {
            if (nuevaContrasena.equals(confirmacionContrasena)) {
                usuarioActual.setContrasena(passwordEncoder.encode(nuevaContrasena));
                repositorioUsuario.save(usuarioActual);
                return "redirect:/perfil";
            } else {
                return "redirect:/perfil";
            }
        } else {
            return "redirect:/perfil";
        }
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @PostMapping("/guardar_cambios")
    public String guardarCambios(@ModelAttribute("nuevopaciente") Paciente paciente,
                                 @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        Usuario usuarioActual = repositorioUsuario.findUsuarioByNombre(username);
        if(usuarioActual != null && usuarioActual.getRol().equals("PACIENTE")){
            Paciente nuevoPaciente = repositorioPaciente.findPacienteByUsuario(usuarioActual);
            nuevoPaciente.setNombre_paciente(paciente.getNombre_paciente());
            nuevoPaciente.setApellidos_paciente(paciente.getApellidos_paciente());
            nuevoPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
            nuevoPaciente.setSexo(paciente.getSexo());
            nuevoPaciente.setDireccion(paciente.getDireccion());
            nuevoPaciente.setCorreo_electronico(paciente.getCorreo_electronico());
            nuevoPaciente.setTelefono(paciente.getTelefono());
            nuevoPaciente.setUsuario(usuarioActual);
            usuarioActual.setNombre(paciente.getUsuario().getNombre());
            repositorioPaciente.save(nuevoPaciente);
            repositorioUsuario.save(usuarioActual);
        }
        return "redirect:/perfil";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/consulta_en_linea")
    public String consultaEnLinea(){
        return "consulta_en_linea";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/historial_clinico")
    public String historialClinico(){
        return "historial_clinico";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/seguimiento_salud")
    public String seguimientoSalud(){
        return "seguimiento_salud";
    }

    @PreAuthorize("hasRole('PACIENTE')")
    @GetMapping("/mensajes")
    public String mensajes(){
        return "mensajes_paciente";
    }

}
