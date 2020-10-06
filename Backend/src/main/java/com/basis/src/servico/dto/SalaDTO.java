package com.basis.src.servico.dto;

import com.basis.src.dominio.Sala;
import com.basis.src.dominio.SalaEquipamento;
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

    private List<SalaEquipamento> equipamentos;

}
