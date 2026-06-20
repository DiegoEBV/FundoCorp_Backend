package com.fundocorp.backend.controller;

import com.fundocorp.backend.dto.BombaCommand;
import com.fundocorp.backend.dto.ValveCommand;
import com.fundocorp.backend.entity.LecturaSensor;
import com.fundocorp.backend.service.ControladorService;
import com.fundocorp.backend.service.LecturaSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

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
                    LecturaSensor ultima = lecturaSensorService
                            .findLastByControlador(command.getIdControlador())
                            .orElse(new LecturaSensor());

                    LecturaSensor nueva = new LecturaSensor();
                    nueva.setIdControlador(command.getIdControlador());
                    nueva.setValvula(command.getAbrir());
                    nueva.setHumedad(ultima.getHumedad());
                    nueva.setHumedad30(ultima.getHumedad30());
                    nueva.setHumedad60(ultima.getHumedad60());
                    nueva.setHumedad90(ultima.getHumedad90());
                    nueva.setRadiacion(ultima.getRadiacion());
                    nueva.setConductividad(ultima.getConductividad());
                    nueva.setTemperatura(ultima.getTemperatura());
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
