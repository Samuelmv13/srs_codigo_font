package com.src.web.rest;

import com.src.servico.EquipamentoServico;
import com.src.servico.dto.EquipamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoRecurso {

    private final EquipamentoServico equipamentoService;

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> listar(){
        List<EquipamentoDTO> dto = equipamentoService.listar();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> buscarPorId (@PathVariable("id") Integer  id){
        return ResponseEntity.ok(equipamentoService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<EquipamentoDTO> inserir(@RequestBody EquipamentoDTO equipamentoDTO ) throws URISyntaxException {
        EquipamentoDTO dto = equipamentoService.inserir(equipamentoDTO);
        return ResponseEntity.created(new URI("/api/equipamentos")).body(dto);

    }

    @PutMapping
    public ResponseEntity<EquipamentoDTO> alterar(@RequestBody  EquipamentoDTO equipamento){
        EquipamentoDTO dto = equipamentoService.inserir(equipamento);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){
        equipamentoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
