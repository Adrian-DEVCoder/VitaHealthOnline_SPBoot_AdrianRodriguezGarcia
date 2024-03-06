package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "HISTORIALES")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "miSecuencia")
    @SequenceGenerator(name = "miSecuencia", sequenceName = "HISTORIALES_SEQ", allocationSize = 1)
    private int id_historial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    private Date fecha_registro;

    @OneToMany(mappedBy = "historial", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;
}
