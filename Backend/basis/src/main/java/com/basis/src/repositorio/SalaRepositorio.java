package com.basis.src.repositorio;

import com.basis.src.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepositorio extends JpaRepository<Sala, Integer> {
}
