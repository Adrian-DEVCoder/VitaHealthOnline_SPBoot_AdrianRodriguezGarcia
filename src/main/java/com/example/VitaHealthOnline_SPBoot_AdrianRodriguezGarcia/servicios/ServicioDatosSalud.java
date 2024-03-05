package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioDatosSalud;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.DatosSalud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDatosSalud {
    private final RepositorioDatosSalud repositorioDatosSalud;

    @Autowired
    public ServicioDatosSalud(RepositorioDatosSalud repositorioDatosSalud) {
        this.repositorioDatosSalud = repositorioDatosSalud;
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
        return repositorioDatosSalud.findByPacienteIdPaciente(id);
    }
}
