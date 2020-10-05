package com.basis.src.servico.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReservaDto {


    private ClienteDto cliente;

    private SalaDto sala;

    private Timestamp dataIni;

    private Timestamp dataFim;

    private double total;
}
