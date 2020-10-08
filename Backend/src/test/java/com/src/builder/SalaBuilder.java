package com.src.builder;

import com.src.dominio.Sala;
import com.src.dominio.TipoSala;
import com.src.repositorio.SalaRepositorio;
import com.src.servico.SalaServico;
import com.src.servico.dto.SalaDTO;
import com.src.servico.mapper.SalaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;


@Component
public class SalaBuilder extends ConstrutorDeEntidade<Sala>{

    @Autowired
    private SalaRepositorio salaRepositorio;
    @Autowired
    private SalaMapper salaMapper;
    @Autowired
    private SalaServico salaServico;

    @Override
    public Sala construirEntidade() throws ParseException {
        TipoSala tipoSala = new TipoSala();
        tipoSala.setId(1);
        tipoSala.setDescricao("Descrição tipo sala");

        EquipamentoBuilder equipamentoBuilder = new EquipamentoBuilder();

        Sala sala = new Sala();
        sala.setCapacidadePessoas(30);
        sala.setDescricao("Descrição sala");
        sala.setDisponivel(1);
        sala.setPrecoDiario(120.0);
        sala.setTipoSala(tipoSala);
        //sala.setEquipamentos(equipamentoBuilder.obterTodos());
        return sala;
    }

    @Override
    public Sala persistir(Sala entidade) {
        SalaDTO salaDTO = salaMapper.toDto(entidade);
        SalaDTO salaSalva = salaServico.inserir(salaDTO);
        return salaMapper.toEntity(salaSalva);
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
}
