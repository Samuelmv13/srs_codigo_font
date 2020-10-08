package com.src.repositorio;

import com.src.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
}