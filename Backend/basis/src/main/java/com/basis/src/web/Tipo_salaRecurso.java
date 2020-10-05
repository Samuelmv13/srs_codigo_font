package com.basis.src.web;

import com.basis.src.dominio.Tipo_sala;
import com.basis.src.servico.Tipo_salaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/tipo_salas")
@RequiredArgsConstructor
public class Tipo_salaRecurso {

    private Tipo_salaServico tipo_salaServico;

    @GetMapping
    public ResponseEntity<List<Tipo_sala>> listar() {
        return ResponseEntity.ok(tipo_salaServico.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo_sala> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(tipo_salaServico.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Tipo_sala> inserir(@RequestBody Tipo_sala tipo_sala) throws URISyntaxException {
        Tipo_sala sala = tipo_salaServico.inserir(tipo_sala);
        return ResponseEntity.created(new URI("/api/tipo_salas")).body(sala);
    }

    @PutMapping
    public ResponseEntity<Tipo_sala> modificar(@RequestBody Tipo_sala tipo_sala) {
        Tipo_sala sala = tipo_salaServico.atualizar(tipo_sala);
        return ResponseEntity.ok(sala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        tipo_salaServico.deletar(id);
        return ResponseEntity.ok().build();
    }
}
