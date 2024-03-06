package com.example.VitaHealthOnline_SPBoot_AdrianRodriguezGarcia.entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DATOSSALUD")
public class DatosSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "miSecuencia")
    @SequenceGenerator(name = "miSecuencia", sequenceName = "DATOS_SEQ", allocationSize = 1)
    private int id_dato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private Date fecha_dato;
    private String tipo_dato;
    private String valor_dato;
    private String unidad_medida;
}
