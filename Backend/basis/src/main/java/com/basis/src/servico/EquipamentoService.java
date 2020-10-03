package com.basis.src.servico;

import com.basis.src.dominio.Equipamento;
import com.basis.src.repositorio.EquipamentoRepositorio;
import com.basis.src.servico.dto.EquipamentoDTO;
import com.basis.src.servico.mapper.EquipamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoService {

    private EquipamentoRepositorio equipamentoRepositorio;
    private EquipamentoMapper equipamentoMapper;

    public List<EquipamentoDTO> listar(){
        List<Equipamento> equipamentos = equipamentoRepositorio.findAll();
        List<EquipamentoDTO> equipamentosDto = equipamentoMapper.entityToDto(equipamentos);
        return equipamentosDto;
    }

    public EquipamentoDTO buscar(Integer id){
        Equipamento equipamento = equipamentoRepositorio.findById(id).orElse(null);
        EquipamentoDTO equipamentoDTO = equipamentoMapper.entityToDto(equipamento);
        return equipamentoDTO;
    }

    public EquipamentoDTO inserir(EquipamentoDTO equipamento){
        Equipamento equipamentoSalve = equipamentoMapper.dtoToEntity(equipamento);
        equipamentoRepositorio.save(equipamentoSalve);
        return equipamento;
    }

    public void deletar(Integer id){
        equipamentoRepositorio.deleteById(id);
    }
}
