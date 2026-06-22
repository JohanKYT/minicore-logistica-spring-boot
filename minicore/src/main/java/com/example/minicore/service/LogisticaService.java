package com.example.minicore.service;

import com.example.minicore.dto.ReporteDTO;
import com.example.minicore.model.Envio;
import com.example.minicore.model.Repartidor;
import com.example.minicore.repository.EnvioRepository;
import com.example.minicore.repository.RepartidorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LogisticaService {

    private final RepartidorRepository repartidorRepo;
    private final EnvioRepository envioRepo;

    public LogisticaService(RepartidorRepository repartidorRepo, EnvioRepository envioRepo) {
        this.repartidorRepo = repartidorRepo;
        this.envioRepo = envioRepo;
    }

    public List<ReporteDTO> generarReporte(LocalDate inicio, LocalDate fin) {
        List<Repartidor> repartidores = repartidorRepo.findAll();
        List<ReporteDTO> reportes = new ArrayList<>();

        for (Repartidor rep : repartidores) {
            List<Envio> envios = envioRepo.findByRepartidorAndFechaEnvioBetween(rep, inicio, fin);

            if (envios.isEmpty()) {
                reportes.add(new ReporteDTO(rep.getNombre(), 0, BigDecimal.ZERO, "—", "—", BigDecimal.ZERO));
                continue;
            }

            BigDecimal totalKg = BigDecimal.ZERO;
            BigDecimal costoTotal = BigDecimal.ZERO;
            Set<String> zonas = new HashSet<>();
            Set<BigDecimal> tarifas = new HashSet<>();

            for (Envio e : envios) {
                totalKg = totalKg.add(e.getPesoKg());
                BigDecimal costoEnvio = e.getPesoKg().multiply(e.getZona().getTarifaPorKg());
                costoTotal = costoTotal.add(costoEnvio);
                zonas.add(e.getZona().getNombreZona());
                tarifas.add(e.getZona().getTarifaPorKg());
            }

            String zonaStr = zonas.size() > 1 ? "Múltiples" : zonas.iterator().next();
            String tarifaStr = tarifas.size() > 1 ? "Varias" : "$" + tarifas.iterator().next();

            reportes.add(new ReporteDTO(rep.getNombre(), envios.size(), totalKg, zonaStr, tarifaStr, costoTotal));
        }
        return reportes;
    }
}
