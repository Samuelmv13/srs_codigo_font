package com.src.repositorio;

import com.src.dominio.ReservaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaEquipamentoRepositorio extends JpaRepository<ReservaEquipamento, Integer> {
    void deleteAllByReservaId(Integer id);
}
