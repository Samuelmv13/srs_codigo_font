package com.basis.src.web.rest;

import com.basis.src.servico.ReservaServico;
import com.basis.src.servico.dto.ReservaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaRecurso {

    private ReservaServico reservaServico;

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Integer id) {
        ReservaDTO dto = reservaServico.buscar(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listar() {
        List<ReservaDTO> dto = reservaServico.listar();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> inserir(@RequestBody ReservaDTO reserva) throws URISyntaxException {
        ReservaDTO dto = reservaServico.inserir(reserva);
        return ResponseEntity.created(new URI("/api/salas")).body(dto);
    }

    @PutMapping
    public ResponseEntity<ReservaDTO> alterar(@RequestBody ReservaDTO reserva) {
        ReservaDTO dto = reservaServico.inserir(reserva);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        reservaServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}