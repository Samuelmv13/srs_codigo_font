package com.basis.src.servico.dto;

import com.basis.src.dominio.Equipamento;
import com.basis.src.dominio.TipoEquipamento;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EquipamentoDTO {

    private int id;

    private DominioPadraoDTO tipoEquipamento;

    private String nome;

    private Double preco;

    private int obrigatorio;

}
