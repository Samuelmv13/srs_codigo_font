package com.basis.src.repositorio;

import com.basis.src.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
}
