package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Diagnostico;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioDiagnostico extends JpaRepository<Diagnostico, Integer> {
    List<Diagnostico> findByHistorial(Historial historial);
}
