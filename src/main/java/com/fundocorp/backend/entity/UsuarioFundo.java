package com.fundocorp.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuario_fundo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFundo {

    @EmbeddedId
    private UsuarioFundoId id;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;
}
