package com.src.dominio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "reserva")
@Getter
@Setter
public class Reserva implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_sequence")
    @SequenceGenerator(name = "reserva_sequence", allocationSize = 1, sequenceName = "reserva_sequence")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala sala;

    @Column(name = "data_inicio")
    private LocalDate dataIni;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "reserva",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReservaEquipamento> equipamentos;
}