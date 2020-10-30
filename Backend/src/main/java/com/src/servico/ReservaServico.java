package com.src.servico;

import com.src.dominio.Reserva;
import com.src.dominio.ReservaEquipamento;
import com.src.repositorio.ReservaEquipamentoRepositorio;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.dto.EquipamentoDTO;
import com.src.servico.dto.ReservaDTO;
import com.src.servico.excecao.RegraNegocioException;
import com.src.servico.mapper.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServico {

    private final ReservaRepositorio reservaRepositorio;
    private final ReservaEquipamentoRepositorio reservaEquipamentoRepositorio;
    private final ReservaMapper reservaMapper;
    private final EquipamentoServico equipamentoServico;

    public List<ReservaDTO> listar() {
        List<Reserva> lista = reservaRepositorio.findAll();
        List<ReservaDTO> listaDto = reservaMapper.toDto(lista);
        return listaDto;
    }

    public ReservaDTO buscar(Integer id) {
        Reserva reserva = reservaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Reserva não encontrada!"));
        ReservaDTO reservaDto = reservaMapper.toDto(reserva);



        return reservaDto;
    }

    public ReservaDTO inserir(ReservaDTO reservaDto) {
        if (reservaDto.getId() != null){
            Reserva reserva = reservaRepositorio.findById(reservaDto.getId())
                    .orElseThrow(()-> new RegraNegocioException("Reserva não encontrada!"));
        }
        if (!dataDisponivel(reservaDto))
            throw new RegraNegocioException("Data não está mais disponivel!");

        Reserva reserva = reservaMapper.toEntity(reservaDto);
        List<ReservaEquipamento> equipamentos= reserva.getEquipamentos();
        reserva.setEquipamentos(new ArrayList<>());
        reservaRepositorio.save(reserva);

        equipamentos.forEach(equipamento ->{
            equipamento.setReserva(reserva);
            equipamento.getId().setIdReserva(reserva.getId());
        });

        reservaEquipamentoRepositorio.saveAll(equipamentos);
        ReservaDTO reservaDTO = reservaMapper.toDto(reserva);
        return reservaDTO;
    }

    public ReservaDTO atualizar(ReservaDTO reservaDto) {
        if (reservaDto.getId() != null){
            Reserva reserva = reservaRepositorio.findById(reservaDto.getId())
                    .orElseThrow(()-> new RegraNegocioException("Reserva não encontrada!"));
        }

        Reserva reserva = reservaMapper.toEntity(reservaDto);
        List<ReservaEquipamento> equipamentos= reserva.getEquipamentos();
        reserva.setEquipamentos(new ArrayList<>());
        reservaRepositorio.save(reserva);

        equipamentos.forEach(equipamento ->{
            equipamento.setReserva(reserva);
            equipamento.getId().setIdReserva(reserva.getId());
        });

        reservaEquipamentoRepositorio.saveAll(equipamentos);
        ReservaDTO reservaDTO = reservaMapper.toDto(reserva);
        return reservaDTO;
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
        return equipamentoServico.buscar(id);
    }
}
