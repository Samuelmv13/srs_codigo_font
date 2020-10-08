package com.src.web.rest;

import com.src.builder.SalaBuilder;
import com.src.dominio.Sala;
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.Collection;

@RunWith(SpringRunner.class)
@Transactional
public class SalaRecursoIT extends IntTestComum {

    @Autowired
    private SalaBuilder salaBuilder;

    @BeforeEach
    public void limparBanco(){
        Collection<Sala> lista = salaBuilder.obterTodos();
        lista.forEach(sala -> salaBuilder.deletarPorId(sala.getId()));
    }

    @Test
    public void listar() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(get("/api/salas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasSize(1)));
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
        Sala sala = salaBuilder.construir();
        sala.setDescricao("descrição sala novo");

        getMockMvc().perform(put("/api/salas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterToDto(sala))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarPorId() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(delete("/api/salas/"+ sala.getId() ))
                .andExpect(status().isOk());
    }
}
