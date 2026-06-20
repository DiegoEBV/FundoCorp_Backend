package com.fundocorp.backend.service;

import com.fundocorp.backend.dto.LoginRequest;
import com.fundocorp.backend.entity.Usuario;
import com.fundocorp.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> login(LoginRequest request) {
        return usuarioRepository.findByCorreo(request.getCorreo())
                .filter(u -> u.getContrasena().equals(request.getContrasena()));
    }
}
