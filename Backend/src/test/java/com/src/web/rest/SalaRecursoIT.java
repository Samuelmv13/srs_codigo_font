package com.src.web.rest;

import com.src.builder.ReservaBuilder;
import com.src.builder.SalaBuilder;
import com.src.dominio.Reserva;
import com.src.dominio.Sala;
import com.src.servico.SalaServico;
import com.src.servico.dto.ReservaDTO;
import com.src.servico.mapper.ReservaMapper;
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class SalaRecursoIT extends IntTestComum {

    @Autowired
    private SalaBuilder salaBuilder;

    @Autowired
    private ReservaBuilder reservaBuilder;

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private SalaServico salaServico;

    @BeforeEach
    public void limparBanco(){
        salaBuilder.deletarBanco();
    }

    @Test
    public void listar() throws Exception {
        salaBuilder.construir();
        getMockMvc().perform(get("/api/salas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", Matchers.hasSize(1)));
    }

    @Test
    public void buscar() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(get("/api/salas/" + sala.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sala.getId()));

    }

    @Test
    public void salvar() throws Exception {
        Sala sala = salaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/salas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterToDto(sala)))
        ).andExpect(status().isCreated());
    }

    @Test
    public void editar() throws Exception {
        Sala sala = salaBuilder.construirEntidade();

        getMockMvc().perform(put("/api/salas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterToDto(sala))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletar() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(delete("/api/salas/"+ sala.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void isReservada() throws Exception {
        Reserva reserva = reservaBuilder.construir();
        boolean valor = false;
        valor = salaServico.isReservada(reserva.getSala().getId());
        Assert.assertTrue(valor);
    }

    @Test
    public void salvarSalasIguais() throws Exception {
        Sala sala = salaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/salas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterToDto(sala))));
        getMockMvc().perform(post("/api/salas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterToDto(sala))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void buscarInexistente() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(get("/api/salas/" + 89))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletarSalaInexistente() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(delete("/api/salas/" + 131313))
                .andExpect(status().isBadRequest());
    }
    
}
