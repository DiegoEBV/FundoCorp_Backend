package com.fundocorp.backend.controller;

import com.fundocorp.backend.dto.LoginRequest;
import com.fundocorp.backend.entity.Usuario;
import com.fundocorp.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}
