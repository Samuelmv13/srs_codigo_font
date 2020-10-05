package com.basis.src.servico;

import com.basis.src.dominio.Tipo_sala;
import com.basis.src.repositorio.Tipo_salaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class Tipo_salaServico {

    private Tipo_salaRepositorio tipo_salaRepositorio;

    public List<Tipo_sala> listar(){
        List<Tipo_sala> tipos_sala = tipo_salaRepositorio.findAll();
        return tipos_sala;
    }

    public Tipo_sala buscar(Integer id){
        Tipo_sala tipo_sala = tipo_salaRepositorio.findById(id).orElse(null);
        return tipo_sala;
    }

    public Tipo_sala inserir(Tipo_sala tipo_sala){
        Tipo_sala tipo_salaSalva = tipo_salaRepositorio.save(tipo_sala);
        return tipo_salaSalva;
    }

    public Tipo_sala atualizar(Tipo_sala tipo_sala){
        Tipo_sala tipo_salaSalva = tipo_salaRepositorio.save(tipo_sala);
        return tipo_salaSalva;
    }

    public void deletar(Integer id){
        tipo_salaRepositorio.deleteById(id);
    }



}
