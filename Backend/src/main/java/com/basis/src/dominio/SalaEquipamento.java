package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sala_equipamento")
@Getter
@Setter
public class SalaEquipamento implements Serializable {

    @EmbeddedId
    private SalaEquipamentoPK id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("idSala")
    @JoinColumn(name = "id_sala")
    private Sala sala;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("idEquipamento")
    @JoinColumn(name = "id_equipamento")
    private Equipamento equipamento;

    @Column(name = "quantidade")
    private Integer quantidade;

}