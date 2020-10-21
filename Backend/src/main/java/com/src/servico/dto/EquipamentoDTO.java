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
<<<<<<< HEAD
    @Min(1) @Max(3)
    private Integer idTipoEquipamento;

=======
    @Min(0) @Max(1)
    private Integer obrigatorio;
    @Min(1) @Max(3)
    private Integer idTipoEquipamento;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
}
