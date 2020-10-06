package com.basis.src.mapper;

import com.basis.src.dominio.SalaEquipamento;
import com.basis.src.servico.dto.SalaEquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses = {})
public interface SalaEquipamentoMapper extends EntityMapper<SalaEquipamentoDTO, SalaEquipamento>{

    @Override
    @Mapping(source = "idSala", target = "idSala.id")
    @Mapping(source = "idEquipamento", target = "idEquipamento.id")
    @Mapping(source = "idSala", target = "id.idSala")
    @Mapping(source = "idEquipamento", target = "id.idEquipamento")
    SalaEquipamento toEntity(SalaEquipamentoDTO salaEquipamentoDTO);

    @Override
    @Mapping(target = "idSala" , source = "idSala.id")
    @Mapping(target = "idEquipamento", source = "idEquipamento.id")
    SalaEquipamentoDTO toDto(SalaEquipamento salaEquipamento);
}
