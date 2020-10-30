package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter@Setter
public class DominioPadraoDTO {

    private Integer id;
    @NotNull
    private String descricao;

}