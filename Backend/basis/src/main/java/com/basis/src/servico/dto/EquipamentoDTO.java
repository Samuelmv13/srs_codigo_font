package com.basis.src.servico.dto;

import com.basis.src.dominio.Equipamento;
import com.basis.src.dominio.TipoEquipamento;
import lombok.Getter;

@Getter
public class EquipamentoDTO {

    private  Equipamento equipamento;

    private int id;

    private TipoEquipamento idTipoEquipamento;

    private String nome;

    private Double preco;

    private int obrigatorio;

}
