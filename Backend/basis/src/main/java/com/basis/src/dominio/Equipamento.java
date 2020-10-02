package com.basis.src.dominio;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
@Table(name ="equipamento")
public class Equipamento {

    @Id @GeneratedValue(strategy = GenerationType.AUTO )
    @Column(name ="id")
    private int id;

    @ManyToOne
    @JoinColumn(name ="id_tipo_equipamento")
    private TipoEquipamento idTipoEquipamento;

    @Column(name ="nome")
    private String nome;

    @Column(name ="preco_diario")
    private Double preco;

    @Column(name ="obrigatorio")
    private int obrigatorio;


}
