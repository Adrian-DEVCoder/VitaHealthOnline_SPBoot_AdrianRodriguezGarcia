package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, Integer> {
    // No es necesario definir métodos personalizados para operaciones básicas de CRUD
}
