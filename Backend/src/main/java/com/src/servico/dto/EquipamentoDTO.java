package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EquipamentoDTO {

    private Integer id;

    private String nome;

    private Double preco;

    private Integer obrigatorio;
<<<<<<< Updated upstream

=======
    @Min(1) @Max(3)
>>>>>>> Stashed changes
    private Integer idTipoEquipamento;



}
