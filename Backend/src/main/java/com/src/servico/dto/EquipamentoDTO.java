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
    private Double preco;
    @Min(0) @Max(1)
    private Integer obrigatorio;
    @Min(1) @Max(3)
    private Integer idTipoEquipamento;

}
