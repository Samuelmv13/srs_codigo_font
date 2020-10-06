package com.basis.src.servico.mapper;

import com.basis.src.dominio.Equipamento;
import com.basis.src.servico.dto.EquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses = {})
public interface EquipamentoMapper extends EntityMapper<EquipamentoDTO, Equipamento> {

    @Override
    @Mapping(source = "idTipoEquipamento" , target = "tipoEquipamento.id")
    Equipamento toEntity(EquipamentoDTO equipamentoDTO);

    @Override
    @Mapping(target = "idTipoEquipamento", source = "tipoEquipamento.id")
    EquipamentoDTO toDto(Equipamento equipamento);
}
