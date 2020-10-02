package com.basis.src.servico;

import com.basis.src.dominio.Equipamento;
import com.basis.src.repositorio.EquipamentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private EquipamentoRepositorio equipamentoRepositorio;

    public List<Equipamento> listar(){
        return null;
    }

    public Equipamento buscar(int id){
        return null;
    }

    public Equipamento inserir(Equipamento equipamento){
        return null;
    }

    public Equipamento alterar(Equipamento equipamento){
        return null;
    }

    public Equipamento deletar(int equipamento){
        return null;
    }
}
