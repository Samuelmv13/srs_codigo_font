package com.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tipo_sala")
public class TipoSala implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private  String descricao;
}