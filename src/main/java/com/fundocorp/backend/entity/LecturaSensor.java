package com.fundocorp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lectura_sensor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturaSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lectura")
    private Long idLectura;

    @Column(name = "id_controlador", nullable = false)
    private Integer idControlador;

    @Column(name = "humedad", precision = 5, scale = 2)
    private BigDecimal humedad;

    @Column(name = "humedad30", precision = 5, scale = 2)
    private BigDecimal humedad30;

    @Column(name = "humedad60", precision = 5, scale = 2)
    private BigDecimal humedad60;

    @Column(name = "humedad90", precision = 5, scale = 2)
    private BigDecimal humedad90;

    @Column(name = "radiacion", precision = 8, scale = 2)
    private BigDecimal radiacion;

    @Column(name = "conductividad", precision = 5, scale = 2)
    private BigDecimal conductividad;

    @Column(name = "temperatura", precision = 5, scale = 2)
    private BigDecimal temperatura;

    @Column(name = "valvula")
    private Boolean valvula;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
}
