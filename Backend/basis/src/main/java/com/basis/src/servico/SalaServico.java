package com.basis.src.servico;

import com.basis.src.dominio.Sala;
import com.basis.src.repositorio.SalaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaServico {

    private SalaRepositorio repositorio;

    public List<Sala> listar(){
        return null;
    }

    public Sala buscar(Integer id){
        return null;
    }

    @Transactional
    public Sala inserir(Sala sala){
        return null;
    }

    @Transactional
    public Sala atualizar(Sala sala){
        return null;
    }

    @Transactional
    public Sala deletar(Integer id){
        return null;
    }
}
