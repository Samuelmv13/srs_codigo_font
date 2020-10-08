package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

    private String descricao;

    private Integer idTipoSala;

    private Integer capacidadePessoas;

    private Double precoDiario;

    private Integer disponivel;

    private List<SalaEquipamentoDTO> equipamentos;

}
