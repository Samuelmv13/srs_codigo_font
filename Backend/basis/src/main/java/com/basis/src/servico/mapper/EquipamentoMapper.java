package com.basis.src.servico.mapper;

import com.basis.src.dominio.Equipamento;
import com.basis.src.servico.dto.EquipamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring", uses = {})
public interface EquipamentoMapper extends EntityMapper<EquipamentoDTO, Equipamento> {

}

