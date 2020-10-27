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
                .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado, tente novamente.") ));

        return equipamentoDTO;
    }

    public EquipamentoDTO inserir(EquipamentoDTO equipamentoDTO){

        //método put
        if (equipamentoDTO.getId() != null){
            equipamentoRepositorio.findById(equipamentoDTO.getId())
                    .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado."));
            //Não deixa espaço null
        }else if(verificaNull(equipamentoDTO)){
            throw new RegraNegocioException("Preencha todas as informações corretamente.");
        }

        Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDTO);
        equipamentoRepositorio.save(equipamento);
        return equipamentoMapper.toDto(equipamento);
    }

    public void deletar(Integer id){
        equipamentoRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado."));

        equipamentoRepositorio.deleteById(id);
    }

    public boolean verificaNull(EquipamentoDTO equipamentoDTO) {
        boolean verificado = false;
        if (equipamentoDTO.getNome().equals(null) ||
                equipamentoDTO.getIdTipoEquipamento() == null) {
            verificado = true;
        }
        return verificado;
    }

}
