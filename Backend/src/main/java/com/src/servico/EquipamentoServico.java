package com.src.servico;

import com.src.dominio.Equipamento;
import com.src.repositorio.EquipamentoRepositorio;
import com.src.servico.dto.EquipamentoDTO;
import com.src.servico.excecao.RegraNegocioException;
import com.src.servico.mapper.EquipamentoMapper;
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
        EquipamentoDTO equipamentoDTO = equipamentoMapper.toDto(equipamentoRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("equipamento não encontrado")));
        return equipamentoDTO;
    }

    public EquipamentoDTO inserir(EquipamentoDTO equipamentoDTO){
        if (equipamentoDTO.getId() != null){
            Equipamento equipamento = equipamentoRepositorio.findById(equipamentoDTO.getId())
                    .orElseThrow(() -> new RegraNegocioException("equipamento não encontrado"));
        }
        Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDTO);
        equipamentoRepositorio.save(equipamento);
        return equipamentoMapper.toDto(equipamento);
    }

    public void deletar(Integer id){
        equipamentoRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("equipamento não encontrado"));
        equipamentoRepositorio.deleteById(id);
    }
}
