package com.basis.src.servico;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
#//autor = "paulo.teotonio"
public class ReservaServico {

    private final ReservaRepositorio reservaRepositorio;
    private final ReservaMapper ReservaMapper;

    public List<ReservaDTO> listar() {
        List<Reserva> Reservas = ReservaRepositorio.findAll();
        List<ReservaDTO> ReservasDto = ReservaMapper.toDto(Reservas);
        return ReservasDto;
    }

    public ReservaDTO buscar(Integer id) {
        Reserva Reserva = ReservaRepositorio.findById(id).orElse(null);
        ReservaDTO ReservaDto = ReservaMapper.toDto(Reserva);
        return ReservaDto;
    }

    public ReservaDTO adicionar(Reserva Reserva) {
        Reserva ReservaSalvo = ReservaRepositorio.save(Reserva);
        return ReservaMapper.toDto(ReservaSalvo);
    }

    public ReservaDTO atualizar(Reserva Reserva) {
        Reserva ReservaSalvo = ReservaRepositorio.save(Reserva);
        ReservaDTO ReservaDto = ReservaMapper.toDto(ReservaSalvo);
        return ReservaDto;
    }

    public void remover(Integer id) {
        ReservaRepositorio.deleteById(id);
    }
}