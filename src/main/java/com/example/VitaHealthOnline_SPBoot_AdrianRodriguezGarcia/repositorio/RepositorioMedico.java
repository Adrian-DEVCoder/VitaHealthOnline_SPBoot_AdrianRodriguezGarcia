package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.repositorio;

import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Medico;
import com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMedico extends JpaRepository<Medico, Integer> {
    Medico findMedicoByUsuario(Usuario usuario);
    int countByMedico(Medico medico);
}
