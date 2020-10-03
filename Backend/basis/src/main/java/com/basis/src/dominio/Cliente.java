package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente implements Serializable {

	@Id
	@SequenceGenerator(name = "sq_cliente", allocationSize = 1, sequenceName = "sq_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cliente")
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
	
	@Column(name = "e-mail")
	private String email;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name = "rg")
	private String rg;
}
