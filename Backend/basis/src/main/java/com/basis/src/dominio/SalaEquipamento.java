package com.basis.src.dominio;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import javax.persistence.*;

@Entity
    @Data
    @Table(name="sala_equipamento")
    @Getter
    @Setter

//autor = "paulo.teotonio"
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
