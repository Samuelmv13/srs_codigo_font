package com.src.servico;

import com.src.dominio.Equipamento;
import com.src.repositorio.EquipamentoRepositorio;
import com.src.servico.dto.EquipamentoDTO;
<<<<<<< HEAD

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
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
<<<<<<< HEAD

        EquipamentoDTO equipamentoDTO = equipamentoMapper.toDto(equipamentoRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado, tente novamente.") ));

=======
        EquipamentoDTO equipamentoDTO = equipamentoMapper.toDto(equipamentoRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado, tente novamente.") ));
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        return equipamentoDTO;
    }

    public EquipamentoDTO inserir(EquipamentoDTO equipamentoDTO){
<<<<<<< HEAD

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        //método put
        if (equipamentoDTO.getId() != null){
            equipamentoRepositorio.findById(equipamentoDTO.getId())
                    .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado."));
            //Não deixa espaço null
        }else if(verificaNull(equipamentoDTO)){
            throw new RegraNegocioException("Preencha todas as informações corretamente.");
        }
<<<<<<< HEAD

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDTO);
        equipamentoRepositorio.save(equipamento);
        return equipamentoMapper.toDto(equipamento);
    }

    public void deletar(Integer id){
<<<<<<< HEAD


=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        equipamentoRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("Equipamento não encontrado."));
        equipamentoRepositorio.deleteById(id);
    }

    public boolean verificaNull(EquipamentoDTO equipamentoDTO) {
        boolean verificado = false;
        if (equipamentoDTO.getNome().equals(null) ||
<<<<<<< HEAD
                equipamentoDTO.getIdTipoEquipamento() == null) {
=======
                equipamentoDTO.getIdTipoEquipamento() == null ||
                equipamentoDTO.getObrigatorio() == null) {
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
            verificado = true;
        }
        return verificado;
    }
<<<<<<< HEAD

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
}
