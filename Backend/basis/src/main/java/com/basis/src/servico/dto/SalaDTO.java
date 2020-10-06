package com.basis.src.servico.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

    private String descricao;

    private Integer idTipoSala;

    private Integer capacidadePessoas;

    private Double precoDiario;

    private Integer disponivel;


}
