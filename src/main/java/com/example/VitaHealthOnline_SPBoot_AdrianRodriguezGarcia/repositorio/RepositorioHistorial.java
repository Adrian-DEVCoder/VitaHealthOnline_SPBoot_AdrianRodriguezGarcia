package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioHistorial extends JpaRepository<Historial, Integer> {
    // No es necesario definir un método personalizado para obtener un historial por paciente con diagnósticos
    // ya que Spring Data JPA puede manejar relaciones automáticamente
}
