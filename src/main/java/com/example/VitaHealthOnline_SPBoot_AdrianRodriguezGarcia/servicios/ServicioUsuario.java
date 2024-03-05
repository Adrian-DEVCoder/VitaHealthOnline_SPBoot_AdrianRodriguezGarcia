package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Usuario;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {

    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public boolean insertarActualizarUsuario(Usuario usuario) {
        repositorioUsuario.save(usuario);
        return true;
    }

    public List<Usuario> getAllUsuarios() {
        return repositorioUsuario.findAll();
    }

    public Usuario getUsuario(int id) {
        return repositorioUsuario.findById(id).orElse(null);
    }

    public boolean deleteUsuario(int id) {
        repositorioUsuario.deleteById(id);
        return true;
    }

    public Usuario findByNombreAndContrasena(String nombre, String contrasena) {
        return repositorioUsuario.findByNombreAndContrasena(nombre, contrasena);
    }
}
