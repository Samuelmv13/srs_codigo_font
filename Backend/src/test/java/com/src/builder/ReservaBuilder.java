package com.src.builder;

import com.src.dominio.Reserva;
import com.src.servico.ReservaServico;
import com.src.servico.dto.ReservaDTO;
import com.src.servico.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
//paulo.teotonio
@Component
public class ReservaBuilder extends ConstrutorDeEntidade<Reserva>{

    @Autowired
    private ReservaServico reservaServico;

    @Autowired
    private ReservaMapper reservaMapper;

    @Override
    public Reserva construirEntidade() throws ParseException {

        Reserva reserva = new Reserva();
        reserva.setDataFim(LocalDate.now());
        reserva.setDataIni(LocalDate.now());
        reserva.setTotal(800.00);

        return reserva;
    }

    public ReservaDTO converterToDto(Reserva reserva){
        return reservaMapper.toDto(reserva);
    }

    @Override
    protected Reserva persistir(Reserva entidade){
        ReservaDTO dto = reservaMapper.toDto(entidade);
        return  reservaMapper.toEntity(reservaServico.inserir(dto));
    }

    @Override
    protected Collection<Reserva> obterTodos()
    {
        return null;
    }

    @Override
    protected Reserva obterPorId(Integer id)
    {
        return null;
    }

    protected  Reserva obterPorId(Long id){
        return null;
    }
}