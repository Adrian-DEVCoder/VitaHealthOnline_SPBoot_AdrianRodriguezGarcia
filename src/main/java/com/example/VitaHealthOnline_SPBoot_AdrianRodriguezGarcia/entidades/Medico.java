package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEDICOS")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "miSecuencia")
    @SequenceGenerator(name = "miSecuencia", sequenceName = "MEDICOS_SEQ", allocationSize = 1)
    private int id_profesional;

    private String nombre_profesional;
    private String apellidos_profesional;
    private String especialidad;
    private String correo_electronico;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

