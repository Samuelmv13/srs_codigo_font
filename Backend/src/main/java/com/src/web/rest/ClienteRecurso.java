package com.src.web.rest;

import com.src.servico.ClienteServico;
import com.src.servico.dto.ClienteDTO;
import com.src.servico.excecao.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteRecurso {

	private final ClienteServico clienteServico;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
			return ResponseEntity.ok(clienteServico.listar());
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Integer clienteId) {
		return ResponseEntity.ok(clienteServico.buscar(clienteId));
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteDTO clienteDto) throws URISyntaxException {
		ClienteDTO dto = clienteServico.adicionar(clienteDto);
		return ResponseEntity.created(new URI("/api/clientes")).body(dto);
	}

	@PutMapping
	public ResponseEntity<ClienteDTO> atualizar(@Valid @RequestBody ClienteDTO clienteDto) {
		ClienteDTO dto = clienteServico.adicionar(clienteDto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Integer clienteId) {
		clienteServico.remover(clienteId);
		return ResponseEntity.ok().build();
	}
}
