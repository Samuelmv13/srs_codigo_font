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
<<<<<<< HEAD


	@NotNull
	@Size(max=120)
	private String nome;

	@CPF @NotNull
	private String cpf;

	@NotNull @Past
	private LocalDate dtNasc;

	@NotNull @Size(max = 255)
	private String endereco;

	@NotNull @Email @Size(max = 255)
	private String email;

	@NotNull @Size(max = 13, min = 13)
	private String telefone;


	
=======
	@NotEmpty
	@Size(max=120)
	private String nome;
	@CPF @NotNull
	private String cpf;
	@NotNull @Past
	private LocalDate dtNasc;
	@NotNull
	private String endereco;
	@NotNull @Email
	private String email;
	@NotNull @Size(max = 13, min = 13)
	private String telefone;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
	@NotNull @Size(max = 7,min = 7)
	private String rg;

}
