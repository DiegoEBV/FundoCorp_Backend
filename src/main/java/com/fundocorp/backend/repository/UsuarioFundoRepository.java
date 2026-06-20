package com.fundocorp.backend.repository;

import com.fundocorp.backend.entity.UsuarioFundo;
import com.fundocorp.backend.entity.UsuarioFundoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioFundoRepository extends JpaRepository<UsuarioFundo, UsuarioFundoId> {

    @Query("SELECT uf FROM UsuarioFundo uf WHERE uf.id.idFundo = :idFundo")
    List<UsuarioFundo> findByFundoId(@Param("idFundo") Integer idFundo);

    @Query("SELECT uf FROM UsuarioFundo uf WHERE uf.id.idUsuario = :idUsuario")
    List<UsuarioFundo> findByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
