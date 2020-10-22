package com.src.servico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ReservaDTO {

    private Integer id;
    @NotNull
    private Integer idCliente;
    @NotNull
    private Integer idSala;
    @NotNull @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataIni;
    @NotNull @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;
    @NotNull
    private Double total;

}