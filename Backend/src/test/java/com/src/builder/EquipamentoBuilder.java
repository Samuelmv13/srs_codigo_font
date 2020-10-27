package com.src.builder;

import com.src.dominio.Equipamento;
import com.src.dominio.TipoEquipamento;
import com.src.repositorio.EquipamentoRepositorio;
import com.src.servico.EquipamentoServico;
import com.src.servico.dto.EquipamentoDTO;
import com.src.servico.mapper.EquipamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;

@Component
public class EquipamentoBuilder extends ConstrutorDeEntidade<Equipamento> {

    @Autowired
    private EquipamentoRepositorio equipamentoRepositorio;
    @Autowired
    private EquipamentoMapper equipamentoMapper;
    @Autowired
    private EquipamentoServico equipamentoServico;

    @Override
    public Equipamento construirEntidade() throws ParseException {

        TipoEquipamento tipoEquipamento = new TipoEquipamento();
        tipoEquipamento.setId(2);
        tipoEquipamento.setDescricao("Descrição teclado.");

        Equipamento equipamento = new Equipamento();
        equipamento.setNome("teclado");
        equipamento.setPrecoDiaria(12.5);
        equipamento.setTipoEquipamento(tipoEquipamento);
        return equipamento;
    }

    @Override
    public Equipamento persistir(Equipamento entidade) {
        EquipamentoDTO equipamentoDTO = equipamentoMapper.toDto(entidade);
        EquipamentoDTO equipamentoSalvo = equipamentoServico.inserir(equipamentoDTO);
        return equipamentoMapper.toEntity(equipamentoSalvo);
    }

    @Override
    public Collection<Equipamento> obterTodos() {

        return equipamentoRepositorio.findAll();
    }

    @Override
    public Equipamento obterPorId(Integer id) {

        return equipamentoMapper.toEntity(equipamentoServico.buscar(id));
    }

    public void deletarPorId(Integer id){
        equipamentoRepositorio.deleteById(id);
    }

    public EquipamentoDTO converterToDto(Equipamento equipamento){
        return equipamentoMapper.toDto(equipamento);

    }


}