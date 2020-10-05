package com.basis.src.servico;

import com.basis.src.mapper.ReservaMapper;
import com.basis.src.repositorio.ReservaRepositorio;
import com.basis.src.servico.dto.ReservaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//import com.basis.src.servico.mapper.ReservaMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServico {

    private ReservaRepositorio reservaRepositorio;
    private ReservaMapper reservaMapper;

    public List<ReservaDto> listar() {
        List<ReservaDto> listaDto = reservaMapper.toDto(reservaRepositorio.findAll());
        return reservaDto;
    }

    public ReservaDto buscar(Integer id) {
        ReservaDto reservaDto = reservaMapper.toDto(reservaRepositorio.findById(id).orElse(null));
        return reservaDto;
    }

    public ReservaDto inserir(ReservaDto reservaDto) {
        reservaRepositorio.save(reservaDto.toDto(reservaDto));
        return reservaDto;
    }

    public void deletar(Integer id) {
        reservaRepositorio.deleteById(id);
    }
}