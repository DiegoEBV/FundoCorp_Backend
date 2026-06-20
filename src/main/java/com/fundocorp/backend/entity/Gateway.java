package com.fundocorp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gateway")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gateway")
    private Integer idGateway;

    @Column(name = "id_fundo", nullable = false)
    private Integer idFundo;

    @Column(name = "modelo", length = 100)
    private String modelo;

    @Column(name = "ip_gateway", length = 20)
    private String ipGateway;

    @Column(name = "estado", length = 100)
    private String estado;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;
}
