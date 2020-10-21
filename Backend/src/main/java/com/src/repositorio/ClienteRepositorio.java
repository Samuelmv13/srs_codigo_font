package com.src.repositorio;

import com.src.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {


    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);

    boolean existsByEmail(String email);

}
