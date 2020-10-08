package com.src.repositorio;

import com.src.dominio.SalaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaEquipamentoRepositorio extends JpaRepository<SalaEquipamento, Integer> {
}