package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Consulta;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioConsulta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioConsulta {
    private final RepositorioConsulta repositorioConsulta;

    @Autowired
    public ServicioConsulta(RepositorioConsulta repositorioConsulta) {
        this.repositorioConsulta = repositorioConsulta;
    }

    public boolean insertarActualizarConsulta(Consulta consulta) {
        repositorioConsulta.save(consulta);
        return true;
    }

    public List<Consulta> getAllConsultas() {
        return repositorioConsulta.findAll();
    }

    public Consulta getConsulta(int id) {
        return repositorioConsulta.findById(id).orElse(null);
    }

    public boolean deleteConsulta(int id) {
        repositorioConsulta.deleteById(id);
        return true;
    }

    public List<Consulta> obtenerConsultasPorPaciente(int id) {
        return repositorioConsulta.findByPacienteIdPaciente(id);
    }
}
