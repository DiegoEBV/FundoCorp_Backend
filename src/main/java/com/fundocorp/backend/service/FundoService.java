package com.fundocorp.backend.service;

import com.fundocorp.backend.entity.Fundo;
import com.fundocorp.backend.repository.FundoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundoService {

    private final FundoRepository fundoRepository;

    public List<Fundo> findAll() {
        return fundoRepository.findAll();
    }

    public Optional<Fundo> findById(Integer id) {
        return fundoRepository.findById(id);
    }

    public Fundo save(Fundo fundo) {
        return fundoRepository.save(fundo);
    }

    public void deleteById(Integer id) {
        fundoRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return fundoRepository.existsById(id);
    }
}
