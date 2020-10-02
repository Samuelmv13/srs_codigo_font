package com.basis.src.servico;

import com.basis.src.dominio.Cliente;
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

	public Cliente transformaParaObjeto() {
		return new Cliente(nome, cpf, dt_nasc, end, email, telefone, rg);
	}
}
