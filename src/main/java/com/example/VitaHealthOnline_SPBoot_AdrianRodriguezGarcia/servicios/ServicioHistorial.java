package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Historial;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioHistorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioHistorial {

    private final RepositorioHistorial repositorioHistorial;

    @Autowired
    public ServicioHistorial(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
    }

    public boolean insertarActualizarHistorial(Historial historial) {
        repositorioHistorial.save(historial);
        return true;
    }

    public List<Historial> getAllHistoriales() {
        return repositorioHistorial.findAll();
    }

    public Historial getHistorial(int id) {
        return repositorioHistorial.findById(id).orElse(null);
    }

    public boolean deleteHistorial(int id) {
        repositorioHistorial.deleteById(id);
        return true;
    }

    public Historial obtenerHistorialPorPacienteConDiagnosticos(int id) {
        Historial historial = repositorioHistorial.findById(id).orElse(null);
        if (historial != null) {
            historial.getDiagnosticos().size();
        }
        return historial;
    }
}
