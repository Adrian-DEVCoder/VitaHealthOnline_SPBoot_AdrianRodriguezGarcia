package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.controladores;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Usuario;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ControladorRegistro {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    Usuario usuario;

    @GetMapping("/registro")
    public String register(Model modelo){
        modelo.addAttribute("nuevousuario", usuario);
        return "registro";
    }

    @PostMapping("/registro")
    public RedirectView procesarRegistro(@ModelAttribute("nuevousuario") Usuario usuario){
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());

        usuario.setNombre(usuario.getNombre());
        usuario.setContrasena(contrasenaEncriptada);
        usuario.setRol(usuario.getRol().toUpperCase());
        repositorioUsuario.save(usuario);
        return new RedirectView("/login");
    }
}
