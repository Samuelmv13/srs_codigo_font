package com.src.servico.mapper;

import com.src.dominio.Cliente;
import com.src.servico.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente>{

}