package com.src.servico;


import com.src.dominio.Equipamento;
import com.src.dominio.Sala;
import com.src.dominio.SalaEquipamento;
import com.src.repositorio.EquipamentoRepositorio;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.dto.ReservaDTO;
import com.src.servico.dto.SalaEquipamentoDTO;
import com.src.servico.excecao.RegraNegocioException;
import com.src.servico.mapper.EquipamentoMapper;
import com.src.servico.mapper.SalaEquipamentoMapper;
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


    private final SalaRepositorio salaRepositorio;
    private final SalaEquipamentoRepositorio salaEquipamentoRepositorio;
    private final SalaMapper salaMapper;
    private final ReservaServico reservaServico;

    public List<SalaDTO> listar(){
        List<Sala> salas = salaRepositorio.findAll();
        return salaMapper.toDto(salas);
    }

    public SalaDTO buscar(Integer id){
        Sala sala = salaRepositorio.findById(id)
                .orElseThrow(()-> new RegraNegocioException("sala não encontrada"));

        SalaDTO salaDTO = salaMapper.toDto(sala);
        return salaDTO;
    }


    public SalaDTO inserir(SalaDTO salaDTO) throws RegraNegocioException{
        Sala novaSala = salaMapper.toEntity(salaDTO);
        List<SalaEquipamento> equipamentos = novaSala.getEquipamentos();
        novaSala.setEquipamentos(new ArrayList<>());

        for (SalaDTO salaInst: listar()){
            if (salaInst.getDescricao().equals(novaSala.getDescricao()) || salaInst.getEquipamentos().equals(salaDTO.getEquipamentos())){
                throw new RegraNegocioException("Salas iguais");
            }
        }
        salaRepositorio.save(novaSala);
        equipamentos.forEach(equipamento ->{
            equipamento.setSala(novaSala);
            equipamento.getId().setIdSala(novaSala.getId());
        });
        salaEquipamentoRepositorio.saveAll(equipamentos);
        SalaDTO salaDTO1 = salaMapper.toDto((novaSala));
        return salaDTO1;
    }

    public void deletar(Integer id){
        Sala sala = salaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Sala com o id " + id + " não existe"));
        if (sala.getDisponivel() == 0) {
            throw new RegraNegocioException("Esta sala não pode ser deletada devido a estar reservada.");
        }
        else {
            salaEquipamentoRepositorio.deleteAllBySalaId(id);
            salaRepositorio.deleteById(id);
        }
    }

    public boolean isReservada(Integer id){
        for (ReservaDTO reserva: reservaServico.listar()) {
            if(reserva.getIdSala() == id){
                return true;
            }
        }
        return false;
    }

}
