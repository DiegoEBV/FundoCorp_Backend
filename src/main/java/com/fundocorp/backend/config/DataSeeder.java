package com.fundocorp.backend.config;

import com.fundocorp.backend.entity.*;
import com.fundocorp.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationRunner {

    private final FundoRepository fundoRepository;
    private final GatewayRepository gatewayRepository;
    private final ControladorRepository controladorRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioFundoRepository usuarioFundoRepository;
    private final LecturaSensorRepository lecturaSensorRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (fundoRepository.count() > 0) return;

        Fundo f1 = fundoRepository.save(new Fundo(null, "Fundo Villacurí Grande", "Ica - Villacurí", new BigDecimal("2200")));
        Fundo f2 = fundoRepository.save(new Fundo(null, "Fundo Salas Guadalupe", "Ica - Salas", new BigDecimal("1500")));
        Fundo f3 = fundoRepository.save(new Fundo(null, "Fundo Chincha Sur", "Chincha - El Carmen", new BigDecimal("800")));
        fundoRepository.save(new Fundo(null, "Fundo Pisco Alto", "Pisco - Humay", new BigDecimal("500")));

        Gateway gw1 = gatewayRepository.save(new Gateway(null, f1.getIdFundo(), "Milesight UG67", "192.168.0.22", "ACTIVO", "Caseta Principal Villacurí"));
        Gateway gw2 = gatewayRepository.save(new Gateway(null, f1.getIdFundo(), "Milesight UG67", "192.168.0.23", "ACTIVO", "Pozo 2 Villacurí"));
        Gateway gw3 = gatewayRepository.save(new Gateway(null, f2.getIdFundo(), "RAK WisGate Edge Pro", "192.168.0.24", "ACTIVO", "Caseta Control Salas"));
        gatewayRepository.save(new Gateway(null, f3.getIdFundo(), "Dragino DLOS8N", "192.168.0.25", "ACTIVO", "Sector A Chincha"));
        gatewayRepository.save(new Gateway(null, f3.getIdFundo(), "Milesight UG67", "192.168.0.26", "INACTIVO", "Límite Norte Pisco"));

        Controlador c1 = controladorRepository.save(new Controlador(null, gw1.getIdGateway(), "Controlador Válvula Sector 1", "Milesight UC511", "Válvula Sector 1 - Lote A"));
        Controlador c2 = controladorRepository.save(new Controlador(null, gw1.getIdGateway(), "Controlador Válvula Sector 2", "Milesight UC511", "Válvula Sector 2 - Lote A"));
        Controlador c3 = controladorRepository.save(new Controlador(null, gw2.getIdGateway(), "Bomba Pozo Profundo 1", "Advantech ECU-1251", "Pozo 1 - Estación Eléctrica"));
        Controlador c4 = controladorRepository.save(new Controlador(null, gw3.getIdGateway(), "Controlador Válvula Sector 3", "Milesight UC511", "Válvula Sector 3 - Salas Lote B"));
        Controlador c5 = controladorRepository.save(new Controlador(null, gw3.getIdGateway(), "Bomba Pozo Profundo 2", "Advantech ECU-1251", "Pozo 2 - Salas Lote B"));

        Usuario u1 = usuarioRepository.save(new Usuario(null, "Diego Ballon", "agronomo@fundocorp.com", "123456", "AGRONOMO"));
        Usuario u2 = usuarioRepository.save(new Usuario(null, "Peter Pacherres", "regulador@ana.gob.pe", "123456", "REGULADOR"));
        Usuario u3 = usuarioRepository.save(new Usuario(null, "Sergio Saavedra", "gerente@fundocorp.com", "123456", "GERENTE"));
        Usuario u4 = usuarioRepository.save(new Usuario(null, "Alessandro Bravo", "agronomo2@fundocorp.com", "123456", "AGRONOMO"));

        usuarioFundoRepository.save(new UsuarioFundo(new UsuarioFundoId(f1.getIdFundo(), u1.getIdUsuario()), LocalDate.of(2026, 1, 10)));
        usuarioFundoRepository.save(new UsuarioFundo(new UsuarioFundoId(f1.getIdFundo(), u3.getIdUsuario()), LocalDate.of(2026, 1, 5)));
        usuarioFundoRepository.save(new UsuarioFundo(new UsuarioFundoId(f2.getIdFundo(), u4.getIdUsuario()), LocalDate.of(2026, 2, 15)));
        usuarioFundoRepository.save(new UsuarioFundo(new UsuarioFundoId(f3.getIdFundo(), u1.getIdUsuario()), LocalDate.of(2026, 3, 1)));

        List<Controlador> ctrlList = List.of(c1, c2, c3, c4, c5);
        LocalDateTime now = LocalDateTime.now();
        for (int i = 12; i >= 0; i--) {
            LocalDateTime ts = now.minusHours(i * 2L);
            for (Controlador ctrl : ctrlList) {
                LecturaSensor ls = new LecturaSensor();
                ls.setIdControlador(ctrl.getIdControlador());
                ls.setHumedad(new BigDecimal("28.00"));
                ls.setHumedad30(new BigDecimal("32.00"));
                ls.setHumedad60(new BigDecimal("28.00"));
                ls.setHumedad90(new BigDecimal("25.00"));
                ls.setRadiacion(new BigDecimal("450.00"));
                ls.setConductividad(new BigDecimal("1.40"));
                ls.setTemperatura(new BigDecimal("22.50"));
                ls.setValvula(false);
                ls.setFechaHora(ts);
                lecturaSensorRepository.save(ls);
            }
        }
    }
}
