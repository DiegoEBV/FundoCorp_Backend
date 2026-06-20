package com.fundocorp.backend.repository;

import com.fundocorp.backend.entity.LecturaSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturaSensorRepository extends JpaRepository<LecturaSensor, Long> {
    List<LecturaSensor> findByIdControladorOrderByFechaHoraDesc(Integer idControlador);
    Optional<LecturaSensor> findFirstByIdControladorOrderByFechaHoraDesc(Integer idControlador);
}
