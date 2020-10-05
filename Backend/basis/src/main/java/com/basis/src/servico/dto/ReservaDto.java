package com.basis.src.servico.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.sql.Timestamp;

@Getter
@Setter
//autor = "paulo.teotonio"
public class ReservaDto {


    private Cliente cliente;

    private Timestamp dataIni;

    private Timestamp dataFim;
}
