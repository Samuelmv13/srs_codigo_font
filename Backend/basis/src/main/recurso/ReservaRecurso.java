package com.basis.src.recurso;


import com.basis.src.dominio.dto.*;
import com.basis.src.repositorio.SalaEquipamentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@RequestMapping("api/reserva")
@RequiredArgsConstructor
public class ReservaRecurso {

    @GetMapping("/{id}")
    public ResponseEntity<InfoReservaDto> buscar (@PathVariable Integer reserva) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<ListaReservaDto>>listar (){
        return null;
    }

    @PostMapping
    public ResponseEntity<RetornoPadraoDto> inserir (@RequestBody InserirReservaDto novaReserva){
        return null;
    }

    @PutMapping
    public ResponseEntity<RetornoPadraoDto> alterar (@RequestBody AtualizarReservaDto reservaAtualizada) { return null; }

    @DeleteMapping("/{id}")
    public ResponseEntity<RetornoPadraoDto> deletar(@PathVariable Integer reserva) { return null;
    }
}
