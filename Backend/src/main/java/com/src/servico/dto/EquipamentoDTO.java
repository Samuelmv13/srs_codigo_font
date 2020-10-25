package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter@Setter
public class EquipamentoDTO {

    private Integer id;
    @NotNull
    private String nome;
    @NotNull @Min(0)
    private Double precoDiaria;
    @Min(1) @Max(3)
    private Integer idTipoEquipamento;


}
