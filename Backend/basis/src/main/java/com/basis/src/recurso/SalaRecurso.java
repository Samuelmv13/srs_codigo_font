package com.basis.src.recurso;

import com.basis.src.dominio.Sala;
import com.basis.src.dominio.Tipo_sala;
import com.basis.src.servico.SalaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaRecurso {

    private SalaServico salaServico;

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return null;
    }

    @GetMapping
    public ResponseEntity<Sala> buscar(@PathVariable Integer id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Sala> inserir(@RequestBody Tipo_sala tipo_sala){
        return null;
    }

    @PutMapping
    public ResponseEntity<Sala> modificar(@RequestBody Tipo_sala tipo_sala) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Sala> deletar(@PathVariable Integer id) {
        return null;
    }

}