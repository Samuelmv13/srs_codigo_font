package com.basis.src.servico.mapper;

import java.util.List;

public interface EntityMapper<Dto, Entity> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

    List<Entity> toEntity(List<Dto> dtoList);

    List<Dto> toDto(List<Entity> entityList);
}
