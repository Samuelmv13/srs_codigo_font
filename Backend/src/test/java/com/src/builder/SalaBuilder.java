package com.src.builder;

import com.src.dominio.Equipamento;
import com.src.dominio.Sala;
import com.src.dominio.SalaEquipamento;
import com.src.dominio.TipoSala;
import com.src.repositorio.SalaRepositorio;
import com.src.servico.SalaServico;
import com.src.servico.dto.SalaDTO;
import com.src.servico.mapper.SalaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;


@Component
public class SalaBuilder extends ConstrutorDeEntidade<Sala>{

    @Autowired
    private SalaRepositorio salaRepositorio;
    @Autowired
    private SalaMapper salaMapper;
    @Autowired
    private SalaServico salaServico;
    @Autowired
    private EquipamentoBuilder equipamentoBuilder;

    @Override
    public Sala construirEntidade() throws ParseException {
<<<<<<< HEAD
        TipoSala tipoSala = new TipoSala();
        tipoSala.setDescricao("Descrição tipo sala");
        tipoSala.setId(1);

=======
        Equipamento equipamentos = equipamentoBuilder.construir();
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        Sala sala = new Sala();
        sala.setCapacidadePessoas(25);
        sala.setDescricao("Sala descrição exemplo");
        sala.setDisponivel(1);
        sala.setPrecoDiario(120.90);
<<<<<<< HEAD
        sala.setTipoSala(tipoSala);

        Equipamento equipamentos = equipamentoBuilder.construir();
=======

        TipoSala tipoSala = new TipoSala();
        tipoSala.setId(1);

>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        SalaEquipamento salaEquipamento = new SalaEquipamento();
        salaEquipamento.setSala(sala);
        salaEquipamento.setQuantidade(20);
        salaEquipamento.setEquipamento(equipamentos);

        sala.setEquipamentos(Collections.singletonList(salaEquipamento));
<<<<<<< HEAD

=======
        sala.setTipoSala(tipoSala);
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        return sala;
    }

    @Override
    public Sala persistir(Sala entidade) {
<<<<<<< HEAD
        SalaDTO salaDTO = salaServico.inserir(salaMapper.toDto(entidade));
        return salaMapper.toEntity(salaDTO);
=======
        SalaDTO salaDTO = salaMapper.toDto(entidade);
        SalaDTO salaSalva = salaServico.inserir(salaDTO);
        return salaMapper.toEntity(salaSalva);
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    }

    @Override
    public Collection<Sala> obterTodos() {
        return salaRepositorio.findAll();
    }

    @Override
    public Sala obterPorId(Integer id) {
        return salaMapper.toEntity(salaServico.buscar(id));
    }

    public void deletarPorId(Integer id){
        salaRepositorio.deleteById(id);
    }

    public SalaDTO converterToDto(Sala sala){
        return salaMapper.toDto(sala);
    }
<<<<<<< HEAD

    public void deletarBanco() {
        salaRepositorio.deleteAll();
    }
}
=======
}
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
