package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;
    @Column(name = "id_tipo_sala")
    private Integer id_tipo_sala;
    @Column(name = "capacidade_pessoas")
    private Integer capacidade_pessoas;
    @Column(name = "preco_diario")
    private Double preco_diario;


}
