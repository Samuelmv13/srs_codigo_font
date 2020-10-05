package com.basis.src.servico;

import com.basis.src.dominio.Sala;
import com.basis.src.mapper.SalaMapper;
import com.basis.src.repositorio.SalaRepositorio;
import com.basis.src.servico.DTO.SalaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalaServico {

    private final SalaRepositorio salaRepositorio;
    private final SalaMapper salaMapper;

    public List<SalaDTO> listar(){
        List<Sala> salas = salaRepositorio.findAll();
        List<SalaDTO> salasDTO = salaMapper.toDto(salas);
        return salasDTO;
    }

    public SalaDTO buscar(Integer id){
        Sala sala = salaRepositorio.findById(id).orElse(null);
        SalaDTO salaDTO = salaMapper.toDto(sala);
        return salaDTO;
    }

    public SalaDTO inserir(Sala sala){
        Sala salaSalva = salaRepositorio.save(sala);
        SalaDTO salaDTO = salaMapper.toDto(salaSalva);
        return salaDTO;
    }

    public SalaDTO atualizar(Sala sala){
        Sala salaSalva = salaRepositorio.save(sala);
        SalaDTO salaDTO = salaMapper.toDto(salaSalva);
        return salaDTO;
    }

    public void deletar(Integer id){
        salaRepositorio.deleteById(id);
    }
}
