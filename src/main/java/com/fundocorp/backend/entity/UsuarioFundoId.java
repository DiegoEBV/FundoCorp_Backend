package com.fundocorp.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFundoId implements Serializable {

    @Column(name = "id_fundo")
    private Integer idFundo;

    @Column(name = "id_usuario")
    private Integer idUsuario;
}
