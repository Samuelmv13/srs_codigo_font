package com.src.web.rest;

import com.src.servico.dto.SalaDTO;
import com.src.servico.SalaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<SalaDTO> inserir(@Valid  @RequestBody SalaDTO salaDTO) throws URISyntaxException {
        SalaDTO dto = salaServico.inserir(salaDTO);
        return ResponseEntity.created(new URI("/api/salas")).body(dto);
    }

    @PutMapping
    public ResponseEntity<SalaDTO> atualizar(@Valid @RequestBody SalaDTO salaDTO) {
        SalaDTO dto = salaServico.atualizar(salaDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        salaServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}