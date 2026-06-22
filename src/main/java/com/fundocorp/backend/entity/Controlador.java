package com.fundocorp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "controlador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Controlador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_controlador")
    private Integer idControlador;

    @Column(name = "id_gateway", nullable = false)
    private Integer idGateway;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "modelo", length = 150)
    private String modelo;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;
}
