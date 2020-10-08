package com.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente implements Serializable {

	@Id
	@SequenceGenerator(name = "cliente_sequence", allocationSize = 1, sequenceName = "cliente_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;

	@Column(name = "dt_nasc")
	private LocalDate dtNasc;

	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "email")
	private String email;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name = "rg")
	private String rg;
}
