package com.basis.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ClienteDTO {

	private String nome;

	private String cpf;

	private Date dt_nasc;

	private String end;

	private String email;

	private String telefone;

	private String rg;

}
