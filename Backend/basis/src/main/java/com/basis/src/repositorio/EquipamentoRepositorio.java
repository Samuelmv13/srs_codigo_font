package com.basis.src.repositorio;

import com.basis.src.dominio.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Integer> {

}
