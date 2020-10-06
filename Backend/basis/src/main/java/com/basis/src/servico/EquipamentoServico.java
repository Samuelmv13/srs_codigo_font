package com.basis.src.servico;

import com.basis.src.repositorio.EquipamentoRepositorio;
import com.basis.src.servico.dto.EquipamentoDTO;
import com.basis.src.servico.mapper.EquipamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoServico {

    private final EquipamentoRepositorio equipamentoRepositorio;
    private final EquipamentoMapper equipamentoMapper;

    public List<EquipamentoDTO> listar(){
        List<EquipamentoDTO> equipamentosDto = equipamentoMapper.toDto(equipamentoRepositorio.findAll());
        return equipamentosDto;
    }

    public EquipamentoDTO buscar(Integer id){
        EquipamentoDTO equipamentoDTO = equipamentoMapper.toDto(equipamentoRepositorio.findById(id).orElse(null));
        return equipamentoDTO;
    }

    public EquipamentoDTO inserir(EquipamentoDTO equipamento){
        equipamentoRepositorio.save(equipamentoMapper.toEntity(equipamento));
        return equipamento;
    }

    public void deletar(Integer id){
        equipamentoRepositorio.deleteById(id);
    }
}
