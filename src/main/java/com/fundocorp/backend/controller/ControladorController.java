package com.fundocorp.backend.controller;

import com.fundocorp.backend.entity.Controlador;
import com.fundocorp.backend.service.ControladorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controladores")
@RequiredArgsConstructor
public class ControladorController {

    private final ControladorService controladorService;

    @GetMapping
    public List<Controlador> getAll() {
        return controladorService.findAll();
    }

    @GetMapping("/gateway/{idGateway}")
    public List<Controlador> getByGateway(@PathVariable Integer idGateway) {
        return controladorService.findByGateway(idGateway);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Controlador> getById(@PathVariable Integer id) {
        return controladorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Controlador create(@RequestBody Controlador controlador) {
        controlador.setIdControlador(null);
        return controladorService.save(controlador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Controlador> update(@PathVariable Integer id, @RequestBody Controlador controlador) {
        return controladorService.findById(id)
                .map(existing -> {
                    controlador.setIdControlador(id);
                    return ResponseEntity.ok(controladorService.save(controlador));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return controladorService.findById(id)
                .map(c -> {
                    controladorService.deleteById(id);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
