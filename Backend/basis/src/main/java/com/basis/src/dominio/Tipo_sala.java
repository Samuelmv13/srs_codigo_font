package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tipo_sala")
public class Tipo_sala implements Serializable {

    @Id
    @SequenceGenerator(name = "sq_tipo_sala", allocationSize = 1, sequenceName = "sq_tipo_sala")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_sala")
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private  String descricao;
}