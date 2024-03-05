package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Diagnostico;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioDiagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDiagnostico {

    private final RepositorioDiagnostico repositorioDiagnostico;

    @Autowired
    public ServicioDiagnostico(RepositorioDiagnostico repositorioDiagnostico) {
        this.repositorioDiagnostico = repositorioDiagnostico;
    }

    public boolean insertarActualizarDiagnostico(Diagnostico diagnostico) {
        repositorioDiagnostico.save(diagnostico);
        return true;
    }

    public List<Diagnostico> getAllDiagnosticos() {
        return repositorioDiagnostico.findAll();
    }

    public Diagnostico getDiagnostico(int id) {
        return repositorioDiagnostico.findById(id).orElse(null);
    }

    public boolean deleteDiagnostico(int id) {
        repositorioDiagnostico.deleteById(id);
        return true;
    }

    public List<Diagnostico> getDiagnosticosByHistorial(int id) {
        return repositorioDiagnostico.findByHistorialIdHistorial(id);
    }
}
