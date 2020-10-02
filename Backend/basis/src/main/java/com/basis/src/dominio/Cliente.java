package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table
@Getter
@Setter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nome;
	
	@Column(length = 11)
	private String cpf;

	private Date dt_nasc;

	private String endereco;
	
	@Column(length = 12)
	private String email;
	
	@Column(length = 12)
	private String telefone;
	
	@Column(length = 7)
	private String rg;

	public Cliente(String nome, String cpf, Date dt_nasc, String endereco, String email, String telefone, String rg) {
		this.nome = nome;
		this.cpf = cpf;
		this.dt_nasc = dt_nasc;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
		this.rg = rg;
	}
}
