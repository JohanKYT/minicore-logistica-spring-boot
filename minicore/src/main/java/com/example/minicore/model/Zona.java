package com.example.minicore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "zonas")
public class Zona {

    @Id
    private Integer idZona;
    private String nombreZona;
    private BigDecimal tarifaPorKg;

    public Zona() {
    }

    public Zona(Integer idZona, String nombreZona, BigDecimal tarifaPorKg) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.tarifaPorKg = tarifaPorKg;
    }

    // Getters y Setters
    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public BigDecimal getTarifaPorKg() {
        return tarifaPorKg;
    }

    public void setTarifaPorKg(BigDecimal tarifaPorKg) {
        this.tarifaPorKg = tarifaPorKg;
    }
}