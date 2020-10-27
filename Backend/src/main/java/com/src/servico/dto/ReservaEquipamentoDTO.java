package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReservaEquipamentoDTO {

    @NotNull
    private Integer idReserva;
    @NotNull
    private Integer idEquipamento;
    @NotNull @Min(0)
    private Integer quantidade;
}