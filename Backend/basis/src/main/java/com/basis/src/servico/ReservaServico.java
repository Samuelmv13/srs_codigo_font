package com.basis.src.servico;

import com.basis.src.repositorio.ReservaRepositorio;
import com.basis.src.servico.dto.ReservaDTO;
import com.basis.src.servico.mapper.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServico {

    private ReservaRepositorio reservaRepositorio;
    private ReservaMapper reservaMapper;

    public List<ReservaDTO> listar(){
        List<ReservaDTO> reservaDto = reservaMapper.entityToDto(reservaRepositorio.findAll());
        return reservaDto;
    }

    public ReservaDTO buscar(Integer id){
        ReservaDTO reservaDto = reservaMapper.entityToDto(reservaRepositorio.findById(id).orElse(null));
        return reservaDto;
    }

    public ReservaDTO inserir(ReservaDTO reservaDto){
        reservaRepositorio.save(reservaMapper.dtoToEntity(reservaDto));
        return reservaDto;
    }

    public void deletar(Integer id){
        reservaRepositorio.deleteById(id);
    }
}