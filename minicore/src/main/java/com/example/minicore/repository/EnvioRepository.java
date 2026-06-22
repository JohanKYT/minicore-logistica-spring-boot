package com.example.minicore.repository;

import com.example.minicore.model.Envio;
import com.example.minicore.model.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    List<Envio> findByRepartidorAndFechaEnvioBetween(Repartidor repartidor, LocalDate inicio, LocalDate fin);
}
