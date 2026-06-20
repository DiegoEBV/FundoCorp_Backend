package com.fundocorp.backend.controller;

import com.fundocorp.backend.entity.LecturaSensor;
import com.fundocorp.backend.service.LecturaSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final LecturaSensorService lecturaSensorService;

    @GetMapping
    public List<LecturaSensor> getAll() {
        return lecturaSensorService.findAll();
    }

    @GetMapping("/controlador/{id}")
    public List<LecturaSensor> getByControlador(@PathVariable Integer id) {
        return lecturaSensorService.findByControlador(id);
    }

    @GetMapping("/controlador/{id}/ultima")
    public ResponseEntity<LecturaSensor> getLastByControlador(@PathVariable Integer id) {
        return lecturaSensorService.findLastByControlador(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LecturaSensor create(@RequestBody LecturaSensor lectura) {
        lectura.setIdLectura(null);
        return lecturaSensorService.save(lectura);
    }
}
