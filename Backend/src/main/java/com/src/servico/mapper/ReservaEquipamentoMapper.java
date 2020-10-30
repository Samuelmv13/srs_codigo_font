package com.src.servico.mapper;

import com.src.dominio.ReservaEquipamento;
import com.src.servico.dto.ReservaEquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses = {})
public interface ReservaEquipamentoMapper extends EntityMapper<ReservaEquipamentoDTO, ReservaEquipamento>{

    @Override
    @Mapping(source = "idReserva", target = "reserva.id")
    @Mapping(source = "idEquipamento", target = "equipamento.id")
    @Mapping(source = "idReserva", target = "id.idReserva")
    @Mapping(source = "idEquipamento", target = "id.idEquipamento")
    ReservaEquipamento toEntity(ReservaEquipamentoDTO reservaEquipamentoDTO);

    @Override
    @Mapping(target = "idReserva" , source = "reserva.id")
    @Mapping(target = "idEquipamento", source = "equipamento.id")
    ReservaEquipamentoDTO toDto(ReservaEquipamento reservaEquipamento);
}
