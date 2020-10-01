package com.basis.src.recurso;

import com.basis.src.dominio.Tipo_sala;
import com.basis.src.repositorio.Tipo_salaRepositorio;
import com.basis.src.servico.Tipo_salaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo_salas")
@RequiredArgsConstructor
public class Tipo_salaRecurso {

    private Tipo_salaServico tipo_salaServico;

    @GetMapping
    public ResponseEntity<List<Tipo_sala>> listar() {
        return null;
    }

    @GetMapping
    public ResponseEntity<Tipo_sala> buscar(@PathVariable Integer id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Tipo_sala> inserir(@RequestBody Tipo_sala tipo_sala){
        return null;
    }

    @PutMapping
    public ResponseEntity<Tipo_sala> modificar(@RequestBody Tipo_sala tipo_sala) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Tipo_sala> deletar(@PathVariable Integer id) {
        return null;
    }
}
