package com.basis.src.dominio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Reserva")
@Getter
@Setter
public class Reserva implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sq_reserva", allocationSize = 1, sequenceName = "sq_reserva")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala id_sala;

    @Column(name = "data_ini")
    private Timestamp dataIni;

    @Column(name = "data_fim")
    private Timestamp dataFim;
}
