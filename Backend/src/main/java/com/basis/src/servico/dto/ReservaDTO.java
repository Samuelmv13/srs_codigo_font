package com.basis.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservaDTO {

    private Integer id;

    private Integer idCliente;

    private Integer idSala;

    private LocalDate dataIni;

    private LocalDate dataFim;

    private Double total;

}