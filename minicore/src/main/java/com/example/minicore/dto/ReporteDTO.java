package com.example.minicore.dto;
import java.math.BigDecimal;

public record ReporteDTO(
        String nombreRepartidor,
        int cantidadEnvios,
        BigDecimal totalKg,
        String zonaInfo,
        String tarifaInfo,
        BigDecimal costoTotal
) {}
