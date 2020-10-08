package com.src.servico.mapper;

import com.src.dominio.Sala;
import com.src.servico.dto.SalaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {SalaEquipamentoMapper.class})
public interface SalaMapper extends EntityMapper<SalaDTO, Sala> {

    @Override
    @Mapping(source = "idTipoSala", target = "tipoSala.id")
    Sala toEntity(SalaDTO salaDTO);

    @Override
    @Mapping(target = "idTipoSala", source =  "tipoSala.id")
    SalaDTO toDto(Sala sala);
}