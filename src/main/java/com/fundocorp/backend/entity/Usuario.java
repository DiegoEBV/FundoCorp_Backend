package com.fundocorp.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombres", length = 255)
    private String nombres;

    @Column(name = "correo", length = 150, unique = true)
    private String correo;

    @JsonIgnore
    @Column(name = "contrasena", length = 255)
    private String contrasena;

    @Column(name = "rol", length = 10)
    private String rol;
}
