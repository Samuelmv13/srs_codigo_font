package com.basis.src.servico;

import com.basis.src.dominio.Cliente;
import com.basis.src.repositorio.ClienteRepositorio;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteServico {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	public List<Cliente> listar() {
		return null;
	}

	public Cliente buscar(Long id) {
		return null;
	}

	@Transactional
	public Cliente adicionar() {
		return null;
	}

	@Transactional
	public Cliente atualizar(Long id) {
		return null;
	}

	@Transactional
	public Cliente remover(Long id) {
		return null;
	}
}
