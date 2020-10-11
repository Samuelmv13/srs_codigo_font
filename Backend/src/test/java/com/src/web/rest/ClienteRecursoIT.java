package com.src.web.rest;

import com.src.builder.ClienteBuilder;
import com.src.dominio.Cliente;
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@Transactional
public class ClienteRecursoIT extends IntTestComum {


    @Autowired
    private ClienteBuilder clienteBuilder;

    @BeforeEach
    public void limparBanco(){
        clienteBuilder.cleanBank();
    }

    @Test
    public void listar() throws Exception{
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasSize(1)));
    }

    @Test
    public void salvar() throws Exception {
        Cliente cliente = clienteBuilder.construirEntidade();
        getMockMvc().perform(post("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterToDto(cliente)))
        ).andExpect(status().isCreated());
    }

    @Test
    public void buscar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(get("/api/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId()));
    }

    @Test
    public void atualizar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(put("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterToDto(cliente)))
        ).andExpect(status().isOk());
    }

    @Test
    public void delete() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(get("/api/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId()));
    }
}
