package com.src.web.rest;

import com.src.builder.ClienteBuilder;
<<<<<<< HEAD
import com.src.builder.ReservaBuilder;
import com.src.dominio.Cliente;
import com.src.dominio.Reserva;
=======
import com.src.dominio.Cliente;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
<<<<<<< HEAD

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


<<<<<<< HEAD

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
@RunWith(SpringRunner.class)
@Transactional
public class ClienteRecursoIT extends IntTestComum {

<<<<<<< HEAD

    @Autowired
    private ClienteBuilder clienteBuilder;
    @Autowired
    private ReservaBuilder reservaBuilder;

=======
    @Autowired
    private ClienteBuilder clienteBuilder;
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be

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
<<<<<<< HEAD
    public void salvarCpfIgual() throws Exception {
        Cliente cliente1 = clienteBuilder.construir();
        Cliente cliente2 = clienteBuilder.construirEntidade();
        postBadRequest(cliente2);
    }

    @Test
    public void salvarRgIgual() throws Exception {
        Cliente cliente1 = clienteBuilder.construir();
        Cliente cliente2 = clienteBuilder.construirEntidade();
        cliente2.setCpf("49824393030");
        postBadRequest(cliente2);
    }

    @Test
    public void salvarEmailIgual() throws Exception {
        Cliente cliente1 = clienteBuilder.construir();
        Cliente cliente2 = clienteBuilder.construirEntidade();
        cliente2.setCpf("49824393030");
        cliente2.setRg("123321");
        postBadRequest(cliente2);
    }
    @Test
    public void salvarRgComLetra() throws Exception {
        Cliente cliente2 = clienteBuilder.construirEntidade();
        cliente2.setRg("123321A");
        postBadRequest(cliente2);
    }
    @Test
    public void salvarCPFInvalido() throws Exception {
        Cliente cliente2 = clienteBuilder.construirEntidade();
        cliente2.setCpf("4982439303A");
        postBadRequest(cliente2);
    }

    @Test

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    public void buscar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(get("/api/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId()));
    }

    @Test
<<<<<<< HEAD
    public void buscarIdInexistente() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        cliente.setId(4568);
        getMockMvc().perform(get("/api/clientes/" + cliente.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test

=======
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    public void atualizar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(put("/api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterToDto(cliente)))
        ).andExpect(status().isOk());
    }

    @Test
<<<<<<< HEAD
    public void atualizarIdInexistente() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        cliente.setId(654684);
        getMockMvc().perform(put("/api/clientes"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deletar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(delete("/api/clientes/" + cliente.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarIdInexistent() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        cliente.setId(654684);
        getMockMvc().perform(delete("/api/clientes/" + cliente.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletarComReserva() throws Exception {
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(delete("/api/clientes/"+ reserva.getCliente().getId()))
                .andExpect(status().isBadRequest());
    }

    private void postBadRequest(Cliente cliente) throws Exception{
        getMockMvc().perform(post("/api/clientes/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterToDto(cliente)))
        ).andExpect(status().isBadRequest());

=======
    public void delete() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(get("/api/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente.getId()));
>>>>>>> 7933ea607a2a5275ae05af77a4004d275265d1be
    }
}
