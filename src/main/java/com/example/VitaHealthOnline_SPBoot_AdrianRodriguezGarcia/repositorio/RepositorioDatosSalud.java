package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.DatosSalud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDatosSalud extends JpaRepository<DatosSalud, Integer> {
    List<DatosSalud> findByPacienteIdPaciente(int id);
}
