package com.src.servico;

import com.src.dominio.Reserva;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.dto.EquipamentoDTO;
import com.src.servico.dto.ReservaDTO;
import com.src.servico.excecao.RegraNegocioException;
import com.src.servico.mapper.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServico {

    private final ReservaRepositorio reservaRepositorio;
    private final ReservaMapper reservaMapper;
    private final EquipamentoServico equipamentoServico;


    public List<ReservaDTO> listar() {
        List<ReservaDTO> listaDto = reservaMapper.toDto(reservaRepositorio.findAll());
        return listaDto;
    }

    public ReservaDTO buscar(Integer id) {

        ReservaDTO reservaDto = reservaMapper.toDto(reservaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Reserva não encontrada!")));

        return reservaDto;
    }

    public ReservaDTO inserir(ReservaDTO reservaDto) {

        if (reservaDto.getId() != null){
            Reserva reserva = reservaRepositorio.findById(reservaDto.getId())
                    .orElseThrow(()-> new RegraNegocioException("reserva não encontrada"));
        }

        Reserva reserva = reservaMapper.toEntity(reservaDto);
        reservaRepositorio.save(reserva);
        return reservaMapper.toDto(reserva);
    }

    public void deletar(Integer id) {

        reservaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Reserva não encontrada!"));

        reservaRepositorio.deleteById(id);
    }
    public boolean dataDisponivel(ReservaDTO reservaDto){
        LocalDate DataIni = reservaDto.getDataIni();
        LocalDate DataFim = reservaDto.getDataFim();
        List<Reserva> reservas = reservaRepositorio.findAllBySalaId(reservaDto.getIdSala());
        boolean dataDisponivel = reservas.stream().allMatch(
                reserva -> DataIni.isAfter(reserva.getDataFim()) ||
                        DataFim.isBefore(reserva.getDataIni()));
        return dataDisponivel;
    }

    private EquipamentoDTO buscarEquipamento(Integer id){
        return this.equipamentoServico.buscar(id);
    }

}
