package com.src.servico;

import com.src.dominio.Sala;
import com.src.dominio.SalaEquipamento;
import com.src.servico.excecao.RegraNegocioException;
import com.src.servico.mapper.SalaMapper;
import com.src.repositorio.SalaEquipamentoRepositorio;
import com.src.repositorio.SalaRepositorio;
import com.src.servico.dto.SalaDTO;
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
        Sala sala = salaRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("sala não encontrada"));
        SalaDTO salaDTO = salaMapper.toDto(sala);
        return salaDTO;
    }

    public SalaDTO inserir(SalaDTO salaDTO){
        if (salaDTO.getId() != null){
            Sala sala = salaRepositorio.findById(salaDTO.getId())
                    .orElseThrow(() -> new RegraNegocioException("Sala não encontrada"));
        }
        Sala sala = salaMapper.toEntity(salaDTO);
        List<SalaEquipamento> equipamentos = sala.getEquipamentos();
        sala.setEquipamentos(new ArrayList<>());
        salaRepositorio.save(sala);
        equipamentos.forEach(equipamento -> {
            equipamento.setSala(sala);
            equipamento.getId().setIdSala(sala.getId());
        });
        salaEquipamentoRepositorio.saveAll(equipamentos);
        return salaMapper.toDto(sala);
    }

    public void deletar(Integer id){
        salaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException(("Sala não encontrada")));
        salaRepositorio.deleteById(id);
    }
}
