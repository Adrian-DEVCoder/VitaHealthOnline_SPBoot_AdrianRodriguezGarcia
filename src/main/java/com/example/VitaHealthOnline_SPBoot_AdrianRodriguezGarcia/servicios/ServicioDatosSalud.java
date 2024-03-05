package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioDatosSalud;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.DatosSalud;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDatosSalud {
    private final RepositorioDatosSalud repositorioDatosSalud;
    private final RepositorioPaciente repositorioPaciente;

    @Autowired
    public ServicioDatosSalud(RepositorioDatosSalud repositorioDatosSalud, RepositorioPaciente repositorioPaciente) {
        this.repositorioDatosSalud = repositorioDatosSalud;
        this.repositorioPaciente = repositorioPaciente;
    }

    public boolean insertarActualizarDatosSalud(DatosSalud datosSalud) {
        repositorioDatosSalud.save(datosSalud);
        return true;
    }

    public List<DatosSalud> getAllDatosSalud() {
        return repositorioDatosSalud.findAll();
    }

    public DatosSalud getDatosSalud(int id) {
        return repositorioDatosSalud.findById(id).orElse(null);
    }

    public boolean deleteDatosSalud(int id) {
        repositorioDatosSalud.deleteById(id);
        return true;
    }

    public List<DatosSalud> obtenerDatosSaludPorPaciente(int id) {
        Paciente paciente = repositorioPaciente.findById(id).orElse(null);
        return repositorioDatosSalud.findByPaciente(paciente);
    }
}
