package com.src.servico;

import com.src.dominio.Reserva;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.dto.ReservaDTO;
<<<<<<< HEAD
import com.src.servico.excecao.RegraNegocioException;
=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
import com.src.servico.mapper.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

<<<<<<< HEAD
=======
//import com.basis.src.servico.mapper.ReservaMapper;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServico {

    private final ReservaRepositorio reservaRepositorio;
    private final ReservaMapper reservaMapper;

<<<<<<< HEAD

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    public List<ReservaDTO> listar() {
        List<ReservaDTO> listaDto = reservaMapper.toDto(reservaRepositorio.findAll());
        return listaDto;
    }

    public ReservaDTO buscar(Integer id) {
<<<<<<< HEAD

        ReservaDTO reservaDto = reservaMapper.toDto(reservaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Reserva não encontrada!")));

=======
        ReservaDTO reservaDto = reservaMapper.toDto(reservaRepositorio.findById(id).orElse(null));
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        return reservaDto;
    }

    public ReservaDTO inserir(ReservaDTO reservaDto) {
<<<<<<< HEAD

        if (reservaDto.getId() != null){
            Reserva reserva = reservaRepositorio.findById(reservaDto.getId())
                    .orElseThrow(()-> new RegraNegocioException("reserva não encontrada"));
        }

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        Reserva reserva = reservaMapper.toEntity(reservaDto);
        reservaRepositorio.save(reserva);
        return reservaMapper.toDto(reserva);
    }

    public void deletar(Integer id) {
<<<<<<< HEAD

        reservaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Reserva não encontrada!"));

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        reservaRepositorio.deleteById(id);
    }
}