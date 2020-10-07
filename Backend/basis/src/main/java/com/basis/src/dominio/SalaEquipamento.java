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
    private SalaEquipamentoKey id;

    @MapsId("idSala")
    @ManyToOne(cascade = CascadeType.PERSIST ,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;

    @MapsId("idEquipamento")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipamento")
    private Equipamento idEquipamento;

    @Column(name = "quantidade")
    private Integer quantidade;
}
;