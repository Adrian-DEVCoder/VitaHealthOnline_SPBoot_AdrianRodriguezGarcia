package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.servicios;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Medico;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio.RepositorioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMedico {

    private final RepositorioMedico repositorioMedico;

    @Autowired
    public ServicioMedico(RepositorioMedico repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public boolean insertarActualizarMedico(Medico medico) {
        repositorioMedico.save(medico);
        return true;
    }

    public List<Medico> getAllMedicos() {
        return repositorioMedico.findAll();
    }

    public Medico getMedico(int id) {
        return repositorioMedico.findById(id).orElse(null);
    }

    public boolean deleteMedico(int id) {
        repositorioMedico.deleteById(id);
        return true;
    }
}
