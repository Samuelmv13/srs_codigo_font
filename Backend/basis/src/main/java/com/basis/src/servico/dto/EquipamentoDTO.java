package com.basis.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EquipamentoDTO {

    private Integer id;

    private DominioPadraoDTO tipoEquipamento;

    private String nome;

    private Double preco;

    private Integer obrigatorio;

    private Integer idTipoEquipamento;



}
