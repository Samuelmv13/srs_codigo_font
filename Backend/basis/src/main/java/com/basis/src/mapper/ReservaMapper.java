package com.basis.src.mapper;

import com.basis.src.dominio.Reserva;
import com.basis.src.servico.dto.ReservaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ReservaMapper extends EntityMapper<ReservaDto, Reserva> {

    @Override
    @Mapping(source = "idTipoSala", target = "tipoSala.id")
    @Mapping(source = "idCliente", target = "cliente.id")
    Reserva dtoToEntity(ReservaDto reserva);

    @Override
    @Mapping(target = "idTipoSala", source = "tipoSala.id")
    @Mapping(target = "idCliente", source = "cliente.id")
    ReservaDto entityToDto(Reserva reserva);
}