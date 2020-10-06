package com.basis.src.servico.mapper;

import com.basis.src.dominio.Sala;
import com.basis.src.servico.dto.SalaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {})
public interface SalaMapper extends EntityMapper<SalaDTO, Sala> {

    @Override
    @Mapping(source = "idTipoSala", target = "tipoSala.id")
    Sala toEntity(SalaDTO salaDTO);

    @Override
    @Mapping(target = "idTipoSala", source =  "tipoSala.id")
    SalaDTO toDto(Sala sala);
}