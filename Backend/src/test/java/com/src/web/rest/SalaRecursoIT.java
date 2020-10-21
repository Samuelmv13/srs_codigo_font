package com.src.web.rest;

<<<<<<< HEAD
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
=======
import com.src.builder.SalaBuilder;
import com.src.dominio.Sala;
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
=======
import static org.hamcrest.Matchers.hasSize;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

<<<<<<< HEAD
=======


import java.util.Collection;

>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
@RunWith(SpringRunner.class)
@Transactional
public class SalaRecursoIT extends IntTestComum {

    @Autowired
    private SalaBuilder salaBuilder;

<<<<<<< HEAD
    @Autowired
    private ReservaBuilder reservaBuilder;

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private SalaServico salaServico;

    @BeforeEach
    public void limparBanco(){
        salaBuilder.deletarBanco();
=======
    @BeforeEach
    public void limparBanco(){
        Collection<Sala> lista = salaBuilder.obterTodos();
        lista.forEach(sala -> salaBuilder.deletarPorId(sala.getId()));
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    }

    @Test
    public void listar() throws Exception {
<<<<<<< HEAD
        salaBuilder.construir();
        getMockMvc().perform(get("/api/salas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", Matchers.hasSize(1)));
=======
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(get("/api/salas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasSize(1)));
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
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
<<<<<<< HEAD
        Sala sala = salaBuilder.construirEntidade();
=======
        Sala sala = salaBuilder.construir();
        sala.setDescricao("descrição sala novo");
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be

        getMockMvc().perform(put("/api/salas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterToDto(sala))))
                .andExpect(status().isOk());
    }

    @Test
<<<<<<< HEAD
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
    
=======
    public void deletarPorId() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(delete("/api/salas/"+ sala.getId() ))
                .andExpect(status().isOk());
    }
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
}
