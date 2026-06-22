package com.fundocorp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "fundo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fundo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fundo")
    private Integer idFundo;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;

    @Column(name = "hectareas", precision = 10, scale = 2)
    private BigDecimal hectareas;
}
