package com.basis.src.servico;

import com.basis.src.dominio.Tipo_sala;
import com.basis.src.repositorio.Tipo_salaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Tipo_salaServico {

    private Tipo_salaRepositorio repositorio;

    public List<Tipo_sala> listar(){
        return null;
    }
    public Tipo_sala buscar(Integer id){
        return null;
    }

    @Transactional
    public Tipo_sala inserir(Tipo_sala tipo_sala){
        return null;
    }

    @Transactional
    public Tipo_sala atualizar(Tipo_sala tipo_sala){
        return null;
    }

    @Transactional
    public Tipo_sala deletar(Integer id){
        return null;
    }



}
