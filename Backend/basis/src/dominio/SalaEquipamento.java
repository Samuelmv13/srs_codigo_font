package com.basis.src.dominio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
    @Entity
    @Data
    @Table(name="sala_equipamento")
    @Getter
    @Setter
public class SalaEquipamento {

        @Embeddable
        private SalaEquipamentoKey id;

    @Column(name="quantidade")
    private int quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSala")
    @JoinColumn(name="id_sala")
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idEquipamento")
    @JoinColumn(name="id_equipamento")
    private Equipamento equipamento;
}
