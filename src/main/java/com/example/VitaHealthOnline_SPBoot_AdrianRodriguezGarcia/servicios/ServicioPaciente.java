package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Usuario;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioPaciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPaciente {

    private final RepositorioPaciente repositorioPaciente;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioPaciente(RepositorioPaciente repositorioPaciente, RepositorioUsuario repositorioUsuario) {
        this.repositorioPaciente = repositorioPaciente;
        this.repositorioUsuario = repositorioUsuario;
    }

    public boolean insertarActualizarPaciente(Paciente paciente) {
        repositorioPaciente.save(paciente);
        return true;
    }

    public List<Paciente> getAllPacientes() {
        return repositorioPaciente.findAll();
    }

    public Paciente getPaciente(int id) {
        return repositorioPaciente.findById(id).orElse(null);
    }

    public boolean deletePaciente(int id) {
        repositorioPaciente.deleteById(id);
        return true;
    }

    public Paciente getPacienteByIdUsuario(int idUsuario) {
        // Primero, obtener el Usuario por su id
        Usuario usuario = repositorioUsuario.findById(idUsuario).orElse(null);
        if (usuario != null) {
            // Si el Usuario existe, obtener el Paciente asociado
            return usuario.getPaciente();
        }
        return null;
    }
}
