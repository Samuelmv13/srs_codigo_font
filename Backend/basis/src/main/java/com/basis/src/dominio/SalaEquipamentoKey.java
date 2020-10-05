package com.basis.src.dominio;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SalaEquipamentoKey implements Serializable {

    @Column(name = "id_sala")
    private Integer idSala;

    @Column(name = "id_equipamento")
    private Integer idEquipamento;
}
