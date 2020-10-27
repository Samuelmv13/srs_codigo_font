package com.src.repositorio;


import com.src.dominio.Cliente;
import com.src.dominio.Reserva;
import com.src.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {

    boolean existsById(Integer id);
    boolean existsByCliente(Cliente cliente);
    boolean existsBySala(Sala sala);
    List<Reserva> findAllBySalaId(Integer id);
    boolean existsByDataIni(LocalDate dataIni);
    boolean existsByDataFim(LocalDate dataFim);

}