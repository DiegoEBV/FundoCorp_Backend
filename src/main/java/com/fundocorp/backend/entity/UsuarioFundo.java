package com.fundocorp.backend.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
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

    // @JsonUnwrapped aplana idFundo/idUsuario al nivel superior del JSON en vez de anidarlos
    // bajo "id", que es como el frontend Angular espera el objeto (idFundo, idUsuario planos).
    @EmbeddedId
    @JsonUnwrapped
    private UsuarioFundoId id;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;
}
