package com.example.minicore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "repartidor")
public class Repartidor {

    @Id
    private Integer idRepartidor;
    private String nombre;
    private String email;

    public Repartidor() {
    }

    public Repartidor(Integer idRepartidor, String nombre, String email) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters
    public Integer getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
