package com.fundocorp.backend.repository;

import com.fundocorp.backend.entity.Fundo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundoRepository extends JpaRepository<Fundo, Integer> {
}
