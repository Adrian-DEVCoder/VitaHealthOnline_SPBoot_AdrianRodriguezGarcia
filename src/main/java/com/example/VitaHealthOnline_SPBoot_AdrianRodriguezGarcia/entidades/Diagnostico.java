package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DIAGNOSTICOS")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "miSecuencia")
    @SequenceGenerator(name = "miSecuencia", sequenceName = "DIAGNOSTICOS_SEQ", allocationSize = 1)
    private int id_diagnostico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historial")
    private Historial historial;

    private String diagnostico;
    private String tratamiento;
}
