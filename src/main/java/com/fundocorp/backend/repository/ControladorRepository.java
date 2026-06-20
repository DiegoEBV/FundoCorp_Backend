package com.fundocorp.backend.repository;

import com.fundocorp.backend.entity.Controlador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControladorRepository extends JpaRepository<Controlador, Integer> {
    List<Controlador> findByIdGateway(Integer idGateway);
}
