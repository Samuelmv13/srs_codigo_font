package com.src.web.rest;

import com.src.builder.ReservaBuilder;
import com.src.dominio.Reserva;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.dto.ReservaDTO;
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//paulo.teotonio
@Transactional
@RunWith(SpringRunner.class)
public class ReservaRecursoIT extends IntTestComum {

    @Autowired
    private ReservaBuilder reservaBuilder;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @BeforeEach
    public void limparBanco(){
        reservaRepositorio.deleteAll();
    }

    //get
    @Test
    public void listar() throws Exception {
        reservaBuilder.construir();
        getMockMvc().perform(get("/api/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasSize(1)));


    }


    @Test
    public void salvar() throws Exception {
        Reserva reserva = reservaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/reservas/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterToDto(reserva)))
        )
                .andExpect(status().isCreated());
    }



    @Test
    public void atualizar()throws Exception{
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(put("/api/reservas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterToDto(reserva)))
        )
                .andExpect(status().isOk());
    }

    //buscarPorId
    @Test
    public void obterPorId()throws Exception{
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(get("/api/reservas/" + reserva.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reserva.getId()));
    }

    //delete
    @Test
    public void deletar() throws Exception{
        Reserva reserva = reservaBuilder.construir();

        getMockMvc().perform(delete("/api/reservas/" + reserva.getId()))
                .andExpect(status().isOk());

    }

}

