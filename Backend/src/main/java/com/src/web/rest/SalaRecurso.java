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
<<<<<<< HEAD

        List<SalaDTO> salaDTOS = salaServico.listar();
        return ResponseEntity.ok(salaDTOS);

=======
        return ResponseEntity.ok(salaServico.listar());
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(salaServico.buscar(id));
    }

    @PostMapping
<<<<<<< HEAD


    public ResponseEntity<SalaDTO> inserir(@Valid @RequestBody SalaDTO salaDTO) throws URISyntaxException {


=======
    public ResponseEntity<SalaDTO> inserir(@Valid  @RequestBody SalaDTO salaDTO) throws URISyntaxException {
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        SalaDTO dto = salaServico.inserir(salaDTO);
        return ResponseEntity.created(new URI("/api/salas")).body(dto);
    }

    @PutMapping
    public ResponseEntity<SalaDTO> atualizar(@Valid @RequestBody SalaDTO salaDTO) {
<<<<<<< HEAD


        SalaDTO dto = salaServico.inserir(salaDTO);


=======
        SalaDTO dto = salaServico.atualizar(salaDTO);
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
<<<<<<< HEAD

    public ResponseEntity<SalaDTO> deletar(@PathVariable Integer id) {

=======
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
        salaServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}