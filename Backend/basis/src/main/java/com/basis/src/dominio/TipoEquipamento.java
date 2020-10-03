package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Getter @Setter
@Table(name = "tipo_equipamento")
public class TipoEquipamento  implements Serializable {

    @Id  @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name = "id")
    private int id;

    @Column(name = "descricao")
    private String descricao;

}
