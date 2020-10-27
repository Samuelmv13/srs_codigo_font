package com.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Getter
@Setter
public class ReservaEquipamentoPK implements Serializable {

    @Column(name = "id_reserva")
    private Integer idReserva;

    @Column(name = "id_equipamento")
    private Integer idEquipamento;
}