package com.basis.src.web.rest;

import com.basis.src.dominio.Equipamento;
import com.basis.src.servico.dto.EquipamentoDTO;
import com.basis.src.servico.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipamento")@RequiredArgsConstructor
public class EquipamentoRecurso {

        private EquipamentoService equipamentoService;

        @GetMapping("/{id}")
        public ResponseEntity<Equipamento> buscar (@PathVariable Integer  equipamento){
            return null;
        }

        @GetMapping
        public ResponseEntity<Equipamento> listar(){
            return null;
        }

        @PostMapping
        public ResponseEntity<Equipamento> inserir(@RequestBody EquipamentoDTO equipamento ){
            return null;
        }

        @PutMapping
        public ResponseEntity<Equipamento> alterar(@RequestBody  EquipamentoDTO equipamento){
            return null;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Equipamento> deletar(@PathVariable Integer equipamento){
            return null;
        }

}
