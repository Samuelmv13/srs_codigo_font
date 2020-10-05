package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "sala")
public class Sala implements Serializable {

    @Id
    @SequenceGenerator(name = "sq_sala", allocationSize = 1, sequenceName = "sq_sala")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_sala")
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "id_tipo_sala")
    private Tipo_sala id_tipo_sala;

    @Column(name = "capacidade_pessoas")
    private Integer capacidade_pessoas;

    @Column(name = "preco_diario")
    private Double preco_diario;


}
