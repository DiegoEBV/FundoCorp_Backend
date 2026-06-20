package com.fundocorp.backend.controller;

import com.fundocorp.backend.entity.Fundo;
import com.fundocorp.backend.service.FundoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fundos")
@RequiredArgsConstructor
public class FundoController {

    private final FundoService fundoService;

    @GetMapping
    public List<Fundo> getAll() {
        return fundoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fundo> getById(@PathVariable Integer id) {
        return fundoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fundo create(@RequestBody Fundo fundo) {
        fundo.setIdFundo(null);
        return fundoService.save(fundo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fundo> update(@PathVariable Integer id, @RequestBody Fundo fundo) {
        if (!fundoService.existsById(id)) return ResponseEntity.notFound().build();
        fundo.setIdFundo(id);
        return ResponseEntity.ok(fundoService.save(fundo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!fundoService.existsById(id)) return ResponseEntity.notFound().build();
        fundoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
