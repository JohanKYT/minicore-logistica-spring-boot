package com.example.minicore.controller;

import com.example.minicore.dto.ReporteDTO;
import com.example.minicore.service.LogisticaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logistica")
@CrossOrigin(origins = "*") // Permite peticiones desde React
public class LogisticaController {

    private final LogisticaService logisticaService;

    public LogisticaController(LogisticaService logisticaService) {
        this.logisticaService = logisticaService;
    }

    @GetMapping("/reporte")
    public List<ReporteDTO> obtenerReporte(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {

        return logisticaService.generarReporte(inicio, fin);
    }
}
