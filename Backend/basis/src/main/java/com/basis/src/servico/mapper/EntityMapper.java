package com.basis.src.servico.mapper;

import java.util.List;

public interface EntityMapper<Dto, Entity> {

    Dto entityToDto(Entity entity);

    Entity dtoToEntity(Dto dto);

    List<Entity> dtoToEntity(List<Dto> dtoList);

    List<Dto> entityToDto(List<Entity> entityList);
}
