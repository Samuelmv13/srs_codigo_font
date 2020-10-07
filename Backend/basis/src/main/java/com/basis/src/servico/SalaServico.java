package com.basis.src.servico;

import com.basis.src.dominio.Sala;
import com.basis.src.dominio.SalaEquipamento;
import com.basis.src.mapper.SalaMapper;
import com.basis.src.repositorio.SalaEquipamentoRepositorio;
import com.basis.src.repositorio.SalaRepositorio;
import com.basis.src.servico.dto.SalaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalaServico {

    private final SalaEquipamentoRepositorio salaEquipamentoRepositorio;
    private final SalaRepositorio salaRepositorio;
    private final SalaMapper salaMapper;

    public List<SalaDTO> listar(){
        List<Sala> salas = salaRepositorio.findAll();
        List<SalaDTO> salasDTO = salaMapper.toDto(salas);
        return salasDTO;
    }

    public SalaDTO buscar(Integer id){
        Sala sala = salaRepositorio.findById(id).orElse(null);
        SalaDTO salaDTO = salaMapper.toDto(sala);
        return salaDTO;
    }

    public SalaDTO inserir(SalaDTO salaDTO){
        Sala sala = salaMapper.toEntity(salaDTO);
        List<SalaEquipamento> equipamentos = sala.getEquipamentos();
        sala.setEquipamentos(new ArrayList<>());
        Sala salaSalva = salaRepositorio.save(sala);
        equipamentos.forEach(equipamento -> {
            equipamento.setIdSala(sala);
            equipamento.getId().setIdSala(sala.getId());
        });
        salaEquipamentoRepositorio.saveAll(equipamentos);
        return salaMapper.toDto(sala);
    }

    public SalaDTO atualizar(SalaDTO salaDTO){
        Sala sala = salaMapper.toEntity(salaDTO);
        Sala salaSalva = salaRepositorio.save(sala);
        SalaDTO dto = salaMapper.toDto(salaSalva);
        return dto;
    }

    public void deletar(Integer id){
        salaRepositorio.deleteById(id);
    }
}
