package com.src.repositorio;

import com.src.dominio.SalaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaEquipamentoRepositorio extends JpaRepository<SalaEquipamento, Integer> {
<<<<<<< HEAD

    void deleteAllBySalaId(Integer id);
    boolean existsByEquipamentoId(Integer id);

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
}