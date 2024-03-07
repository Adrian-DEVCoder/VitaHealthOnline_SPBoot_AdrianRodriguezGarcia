package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Consulta;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioConsulta;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioConsulta {
    private final RepositorioConsulta repositorioConsulta;
    private final RepositorioPaciente repositorioPaciente;

    @Autowired
    public ServicioConsulta(RepositorioConsulta repositorioConsulta, RepositorioPaciente repositorioPaciente) {
        this.repositorioConsulta = repositorioConsulta;
        this.repositorioPaciente = repositorioPaciente;
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
        Paciente paciente = repositorioPaciente.findById(id).orElse(null);
        return repositorioConsulta.findByPaciente(paciente);
    }

    public List<Consulta> filtrarConsultas(String tipoConsulta, String estadoConsulta){
        if(tipoConsulta != null && estadoConsulta != null){
            String tipoConsultaFormateada = tipoConsulta.replace("_"," ");
            return repositorioConsulta.findAllByTipoConsultaAndEstadoConsulta(tipoConsultaFormateada, estadoConsulta);
        } else if (tipoConsulta.equals("..")){
            return repositorioConsulta.findAllByEstadoConsulta(estadoConsulta);
        } else if (estadoConsulta.equals("..")){
            String tipoConsultaFormateada = tipoConsulta.replace("_"," ");
            return repositorioConsulta.findAllByTipoConsulta(tipoConsultaFormateada);
        } else {
            return repositorioConsulta.findAll();
        }
    }
}
