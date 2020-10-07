package com.basis.src.servico.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReservaDto {

    private Integer id;

    private ClienteDTO cliente;

    private SalaDTO sala;

    private Timestamp dataIni;

    private Timestamp dataFim;

    private double total;
}
