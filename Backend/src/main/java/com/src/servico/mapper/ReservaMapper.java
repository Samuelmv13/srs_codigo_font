package com.src.servico.mapper;

import com.src.dominio.Reserva;
import com.src.dominio.ReservaEquipamento;
import com.src.servico.dto.ReservaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReservaEquipamentoMapper.class})
public interface ReservaMapper extends EntityMapper<ReservaDTO, Reserva> {

    @Override
    @Mapping(source = "idSala", target = "sala.id")
    @Mapping(source = "idCliente", target = "cliente.id")
    Reserva toEntity(ReservaDTO reserva);

    @Override
    @Mapping(target = "idSala", source = "sala.id")
    @Mapping(target = "idCliente", source = "cliente.id")
    ReservaDTO toDto(Reserva reserva);
}