package com.basis.src.dominio;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Reserva {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "data_ini")
    private Timestamp dataIni;

    @Column(name = "data_fim")
    private Timestamp dataFim;

    //teste git

    private String mensagem;

    public RetornoPadraoDto(String mensagem){
        this.mensagem = mensagem;
    }
}
