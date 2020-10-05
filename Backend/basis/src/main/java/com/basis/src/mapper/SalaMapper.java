package com.basis.src.mapper;

import com.basis.src.dominio.Sala;
import com.basis.src.servico.DTO.SalaDTO;
import liquibase.pro.packaged.E;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {})
public interface SalaMapper extends EntityMapper<SalaDTO, Sala> {

    Sala toEntity(SalaDTO sala);

    SalaDTO toDto(Sala sala);
}
