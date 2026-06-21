package com.fundocorp.backend.controller;

import com.fundocorp.backend.entity.Gateway;
import com.fundocorp.backend.service.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gateways")
@RequiredArgsConstructor
public class GatewayController {

    private final GatewayService gatewayService;

    @GetMapping
    public List<Gateway> getAll() {
        return gatewayService.findAll();
    }

    @GetMapping("/fundo/{idFundo}")
    public List<Gateway> getByFundo(@PathVariable Integer idFundo) {
        return gatewayService.findByFundo(idFundo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gateway> getById(@PathVariable Integer id) {
        return gatewayService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gateway create(@RequestBody Gateway gateway) {
        gateway.setIdGateway(null);
        return gatewayService.save(gateway);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gateway> update(@PathVariable Integer id, @RequestBody Gateway gateway) {
        return gatewayService.findById(id)
                .map(existing -> {
                    gateway.setIdGateway(id);
                    return ResponseEntity.ok(gatewayService.save(gateway));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return gatewayService.findById(id)
                .map(g -> {
                    gatewayService.deleteById(id);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(ResponseEntity.<Void>notFound().build());
    }
}
