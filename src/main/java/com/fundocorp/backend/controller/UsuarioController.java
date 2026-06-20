package com.fundocorp.backend.controller;

import com.fundocorp.backend.entity.Usuario;
import com.fundocorp.backend.entity.UsuarioFundo;
import com.fundocorp.backend.entity.UsuarioFundoId;
import com.fundocorp.backend.repository.UsuarioFundoRepository;
import com.fundocorp.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioFundoRepository usuarioFundoRepository;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        usuario.setIdUsuario(null);
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.findById(id)
                .map(existing -> {
                    usuario.setIdUsuario(id);
                    return ResponseEntity.ok(usuarioService.save(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return usuarioService.findById(id)
                .map(u -> {
                    usuarioService.deleteById(id);
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/fundo/{idFundo}")
    public List<UsuarioFundo> getByFundo(@PathVariable Integer idFundo) {
        return usuarioFundoRepository.findByFundoId(idFundo);
    }

    @PostMapping("/fundo/{idFundo}/asignar/{idUsuario}")
    public ResponseEntity<UsuarioFundo> asignar(@PathVariable Integer idFundo, @PathVariable Integer idUsuario) {
        UsuarioFundoId pk = new UsuarioFundoId(idFundo, idUsuario);
        if (usuarioFundoRepository.existsById(pk)) {
            return ResponseEntity.ok(usuarioFundoRepository.findById(pk).get());
        }
        return ResponseEntity.ok(usuarioFundoRepository.save(new UsuarioFundo(pk, LocalDate.now())));
    }

    @DeleteMapping("/fundo/{idFundo}/desasignar/{idUsuario}")
    public ResponseEntity<Void> desasignar(@PathVariable Integer idFundo, @PathVariable Integer idUsuario) {
        UsuarioFundoId pk = new UsuarioFundoId(idFundo, idUsuario);
        if (!usuarioFundoRepository.existsById(pk)) return ResponseEntity.notFound().build();
        usuarioFundoRepository.deleteById(pk);
        return ResponseEntity.noContent().build();
    }
}
