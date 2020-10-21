package com.src.repositorio;

<<<<<<< HEAD

import com.src.dominio.Cliente;

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
import com.src.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
<<<<<<< HEAD


    boolean existsById(Integer id);
    boolean existsByCliente(Cliente cliente);

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
}