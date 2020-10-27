package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReservaDTO {

    private Integer id;

    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idSala;

    @NotNull @Past
    private LocalDate dataIni;

    @NotNull
    private LocalDate dataFim;

    private Double total;

    private List<ReservaEquipamentoDTO> equipamentos;

}