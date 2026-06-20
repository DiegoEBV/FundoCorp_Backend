package com.fundocorp.backend.service;

import com.fundocorp.backend.entity.LecturaSensor;
import com.fundocorp.backend.repository.LecturaSensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LecturaSensorService {

    private final LecturaSensorRepository lecturaSensorRepository;

    public List<LecturaSensor> findAll() {
        return lecturaSensorRepository.findAll();
    }

    public List<LecturaSensor> findByControlador(Integer idControlador) {
        return lecturaSensorRepository.findByIdControladorOrderByFechaHoraDesc(idControlador);
    }

    public Optional<LecturaSensor> findLastByControlador(Integer idControlador) {
        return lecturaSensorRepository.findFirstByIdControladorOrderByFechaHoraDesc(idControlador);
    }

    public LecturaSensor save(LecturaSensor lectura) {
        return lecturaSensorRepository.save(lectura);
    }
}
