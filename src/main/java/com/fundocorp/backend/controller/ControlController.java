package com.fundocorp.backend.controller;

import com.fundocorp.backend.dto.BombaCommand;
import com.fundocorp.backend.dto.ValveCommand;
import com.fundocorp.backend.entity.LecturaSensor;
import com.fundocorp.backend.service.ControladorService;
import com.fundocorp.backend.service.LecturaSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/control")
@RequiredArgsConstructor
public class ControlController {

    private final LecturaSensorService lecturaSensorService;
    private final ControladorService controladorService;

    @PostMapping("/valvula")
    public ResponseEntity<Map<String, String>> cambiarValvula(@RequestBody ValveCommand command) {
        return controladorService.findById(command.getIdControlador())
                .map(ctrl -> {
                    // Si el controlador aún no tiene ninguna lectura (recién creado), se usan 0
                    // como valores base en vez de null, para que el frontend pueda graficarlos.
                    Optional<LecturaSensor> ultima = lecturaSensorService
                            .findLastByControlador(command.getIdControlador());

                    LecturaSensor nueva = new LecturaSensor();
                    nueva.setIdControlador(command.getIdControlador());
                    nueva.setValvula(command.getAbrir());
                    nueva.setHumedad(ultima.map(LecturaSensor::getHumedad).orElse(BigDecimal.ZERO));
                    nueva.setHumedad30(ultima.map(LecturaSensor::getHumedad30).orElse(BigDecimal.ZERO));
                    nueva.setHumedad60(ultima.map(LecturaSensor::getHumedad60).orElse(BigDecimal.ZERO));
                    nueva.setHumedad90(ultima.map(LecturaSensor::getHumedad90).orElse(BigDecimal.ZERO));
                    nueva.setRadiacion(ultima.map(LecturaSensor::getRadiacion).orElse(BigDecimal.ZERO));
                    nueva.setConductividad(ultima.map(LecturaSensor::getConductividad).orElse(BigDecimal.ZERO));
                    nueva.setTemperatura(ultima.map(LecturaSensor::getTemperatura).orElse(BigDecimal.ZERO));
                    nueva.setFechaHora(LocalDateTime.now());
                    lecturaSensorService.save(nueva);

                    String estado = Boolean.TRUE.equals(command.getAbrir()) ? "abierto" : "cerrado";
                    return ResponseEntity.ok(Map.of("estado", estado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/bomba")
    public ResponseEntity<Map<String, Object>> ajustarBomba(@RequestBody BombaCommand command) {
        return controladorService.findById(command.getIdControlador())
                .map(ctrl -> {
                    double hz = Math.max(0, Math.min(60, command.getVelocidadValida()));
                    return ResponseEntity.ok(Map.<String, Object>of(
                            "idControlador", command.getIdControlador(),
                            "velocidad", hz
                    ));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
