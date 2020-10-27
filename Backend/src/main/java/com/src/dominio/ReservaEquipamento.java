package com.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reserva_equipamento")
@Getter
@Setter
public class ReservaEquipamento implements Serializable{

    @EmbeddedId
    private ReservaEquipamentoPK id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("idReserva")
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("idEquipamento")
    @JoinColumn(name = "id_equipamento")
    private Equipamento equipamento;

    @Column(name = "quantidade")
    private Integer quantidade;

}