package com.src.repositorio;

import com.src.dominio.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Integer> {

}
