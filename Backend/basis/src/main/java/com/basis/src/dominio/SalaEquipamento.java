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

    @Embeddable
    private SalaEquipamentoKey id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSala")
    @JoinColumn(name = "id_sala")
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEquipamento")
    @JoinColumn(name = "id_equipamento")
    private Equipamento idEquipamento;
}
;