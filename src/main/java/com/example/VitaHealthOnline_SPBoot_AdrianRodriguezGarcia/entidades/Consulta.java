package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CONSULTAS")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "miSecuencia")
    @SequenceGenerator(name = "miSecuencia", sequenceName = "CONSULTAS_SEQ", allocationSize = 1)
    private int id_consulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_consulta;
    private String tipo_consulta;
    private String estado_consulta;
}
