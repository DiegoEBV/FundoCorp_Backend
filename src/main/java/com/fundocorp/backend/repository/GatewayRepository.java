package com.fundocorp.backend.repository;

import com.fundocorp.backend.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Integer> {
    List<Gateway> findByIdFundo(Integer idFundo);
}
