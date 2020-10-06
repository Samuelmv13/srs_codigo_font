package com.basis.src.servico;

import com.basis.src.dominio.Cliente;
import com.basis.src.mapper.ClienteMapper;
import com.basis.src.repositorio.ClienteRepositorio;
import com.basis.src.servico.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ClienteServico {

	private final ClienteRepositorio clienteRepositorio;
	private final ClienteMapper clienteMapper;

	public List<ClienteDTO> listar() {
		List<Cliente> clientes = clienteRepositorio.findAll();
		List<ClienteDTO> clientesDto = clienteMapper.toDto(clientes);
		return clientesDto;
	}

	public ClienteDTO buscar(Integer id) {
		Cliente cliente = clienteRepositorio.findById(id).orElse(null);
		ClienteDTO clienteDto = clienteMapper.toDto(cliente);
		return clienteDto;
	}

	public ClienteDTO adicionar(ClienteDTO clienteDto) {
		Cliente cliente = clienteMapper.toEntity(clienteDto);
		Cliente clienteSalvo = clienteRepositorio.save(cliente);
		return clienteMapper.toDto(clienteSalvo);
	}

	public ClienteDTO atualizar(ClienteDTO clienteDto) {
		Cliente cliente = clienteMapper.toEntity(clienteDto);
		Cliente clienteSalvo = clienteRepositorio.save(cliente);
		return clienteDto;
	}

	public void remover(Integer id) {
		clienteRepositorio.deleteById(id);
	}
}
