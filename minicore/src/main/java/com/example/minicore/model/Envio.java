package com.example.minicore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "envios")
public class Envio {

    @Id
    private Integer idEnvio;

    @ManyToOne
    @JoinColumn(name = "id_repartidor")
    private Repartidor repartidor;

    @ManyToOne
    @JoinColumn(name = "id_zona")
    private Zona zona;

    private BigDecimal pesoKg;
    private LocalDate fechaEnvio;

    public Envio() {
    }

    public Envio(Integer idEnvio, Repartidor repartidor, Zona zona, BigDecimal pesoKg, LocalDate fechaEnvio) {
        this.idEnvio = idEnvio;
        this.repartidor = repartidor;
        this.zona = zona;
        this.pesoKg = pesoKg;
        this.fechaEnvio = fechaEnvio;
    }

    // Getters y Setters
    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
