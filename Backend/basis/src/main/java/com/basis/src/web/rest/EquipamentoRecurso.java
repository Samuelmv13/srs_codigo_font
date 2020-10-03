package com.basis.src.web.rest;

import com.basis.src.dominio.Equipamento;
import com.basis.src.servico.dto.EquipamentoDTO;
import com.basis.src.servico.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")@RequiredArgsConstructor
public class EquipamentoRecurso {

        private EquipamentoService equipamentoService;

        @GetMapping("/{id}")
        public ResponseEntity<EquipamentoDTO> buscarPorId (@PathVariable Integer  id){
            EquipamentoDTO dto = equipamentoService.buscar(id);
            return ResponseEntity.ok(dto);
        }

        @GetMapping
        public ResponseEntity<List<EquipamentoDTO>> listar(){
            List<EquipamentoDTO> dto = equipamentoService.listar();
            return ResponseEntity.ok(dto);
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
        public ResponseEntity<Void> deletar(@PathVariable Integer id){
            equipamentoService.deletar(id);
            return ResponseEntity.ok().build();
        }

}
