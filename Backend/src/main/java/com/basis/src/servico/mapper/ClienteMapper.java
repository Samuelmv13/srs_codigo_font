package com.basis.src.servico.mapper;

import com.basis.src.dominio.Cliente;
import com.basis.src.servico.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente>{

}