package com.fundocorp.backend.service;

import com.fundocorp.backend.entity.Controlador;
import com.fundocorp.backend.repository.ControladorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControladorService {

    private final ControladorRepository controladorRepository;

    public List<Controlador> findAll() {
        return controladorRepository.findAll();
    }

    public List<Controlador> findByGateway(Integer idGateway) {
        return controladorRepository.findByIdGateway(idGateway);
    }

    public Optional<Controlador> findById(Integer id) {
        return controladorRepository.findById(id);
    }

    public Controlador save(Controlador controlador) {
        return controladorRepository.save(controlador);
    }

    public void deleteById(Integer id) {
        controladorRepository.deleteById(id);
    }
}
