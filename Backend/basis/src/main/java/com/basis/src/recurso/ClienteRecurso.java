package com.basis.src.recurso;

import com.basis.src.dominio.Cliente;
import com.basis.src.servico.ClienteDTO;
import com.basis.src.servico.ClienteServico;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteRecurso {
	
	@Autowired
	private ClienteServico clienteServico;

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		return null;
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return null;
	}

	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody ClienteDTO cliente) {
		return null;
	}

	@PutMapping
	public ResponseEntity<Cliente> atualizar(@RequestBody ClienteDTO cliente) {
		return null;
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		return null;
	}
}
