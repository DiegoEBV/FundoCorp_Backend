package com.fundocorp.backend.service;

import com.fundocorp.backend.entity.Gateway;
import com.fundocorp.backend.repository.GatewayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GatewayService {

    private final GatewayRepository gatewayRepository;

    public List<Gateway> findAll() {
        return gatewayRepository.findAll();
    }

    public List<Gateway> findByFundo(Integer idFundo) {
        return gatewayRepository.findByIdFundo(idFundo);
    }

    public Optional<Gateway> findById(Integer id) {
        return gatewayRepository.findById(id);
    }

    public Gateway save(Gateway gateway) {
        return gatewayRepository.save(gateway);
    }

    public void deleteById(Integer id) {
        gatewayRepository.deleteById(id);
    }
}
