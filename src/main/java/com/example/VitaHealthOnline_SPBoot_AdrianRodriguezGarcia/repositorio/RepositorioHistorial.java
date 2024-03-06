package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Historial;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioHistorial extends JpaRepository<Historial, Integer> {
    Historial findHistorialByPaciente(Paciente paciente);
}
