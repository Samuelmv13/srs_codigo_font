package com.basis.src.mapper;

import java.util.List;
//autor = "paulo.teotonio"
public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}