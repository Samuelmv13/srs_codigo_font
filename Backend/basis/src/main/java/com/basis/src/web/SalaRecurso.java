package com.basis.src.web;

import com.basis.src.dominio.Sala;
import com.basis.src.dominio.Tipo_sala;
import com.basis.src.servico.DTO.SalaDTO;
import com.basis.src.servico.SalaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaRecurso {

    private final SalaServico salaServico;

    @GetMapping
    public ResponseEntity<List<SalaDTO>> listar() {
        return ResponseEntity.ok(salaServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(salaServico.buscar(id));
    }

    @PostMapping
    public ResponseEntity<SalaDTO> inserir(@RequestBody Sala sala) throws URISyntaxException {
        SalaDTO dto = salaServico.inserir(sala);
        return ResponseEntity.created(new URI("/api/salas")).body(dto);
    }

    @PutMapping
    public ResponseEntity<SalaDTO> atualizar(@RequestBody Sala sala) {
        SalaDTO dto = salaServico.atualizar(sala);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        salaServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}