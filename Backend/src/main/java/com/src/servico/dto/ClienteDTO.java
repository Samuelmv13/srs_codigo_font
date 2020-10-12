package com.src.servico.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {


	private Integer id;

	@NotEmpty
	@Size(max=120)
	private String nome;

	@CPF @NotNull @Size(min = 11, max = 11)
	private String cpf;

	@NotNull @Past
	private LocalDate dtNasc;

	@NotNull @Size(max = 255)
	private String endereco;

	@NotNull @Email @Size(max = 255)
	private String email;

	@NotNull @Size(max = 13, min = 13)
	private String telefone;

	@NotNull @Size(max = 7,min = 7)
	private String rg;

}
