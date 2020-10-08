package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {

	private Integer id;

	private String nome;

	private String cpf;

	private LocalDate dtNasc;

	private String endereco;

	private String email;

	private String telefone;

	private String rg;

}
