package com.basis.src.web.rest;

<<<<<<< Updated upstream:Backend/src/test/java/com/basis/src/web/rest/EquipamentoRecursoIT.java
import com.basis.src.builder.EquipamentoBuilder;
import com.basis.src.dominio.Equipamento;
import com.basis.src.util.IntTestComum;
import com.basis.src.util.TestUtil;
=======
import com.src.builder.EquipamentoBuilder;
import com.src.dominio.Equipamento;
import com.src.util.IntTestComum;
import com.src.util.TestUtil;
>>>>>>> Stashed changes:Backend/src/test/java/com/src/web/rest/EquipamentoRecursoIT.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@Transactional
public class EquipamentoRecursoIT extends IntTestComum {

    public static final int ID_INVALIDO = 1000;

    @Autowired
    private EquipamentoBuilder equipamentoBuilder;

    @BeforeEach
    public void limparBanco() {
        Collection<Equipamento> lista = equipamentoBuilder.obterTodos();
        lista.forEach(equipamento -> equipamentoBuilder.deletarPorId(equipamento.getId()));
    }

    @Test
    public void listar() throws Exception {
        equipamentoBuilder.construir();
        getMockMvc().perform(get("/api/equipamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", hasSize(1)));
    }

    @Test
    public void buscar() throws Exception{
        Equipamento equipamento = equipamentoBuilder.construir();
        getMockMvc().perform(get("/api/equipamentos/" + equipamento.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(equipamento.getId()));
    }


    @Test
     public void salvar() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construirEntidade();
        getMockMvc().perform(post("/api/equipamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipamentoBuilder.converterToDto(equipamento)))
        ).andExpect(status().isCreated());
    }

    @Test
    public void editar() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construir();
        equipamento.setNome("Teclado novo");
        getMockMvc().perform(put("/api/equipamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipamentoBuilder.converterToDto(equipamento))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarPorId() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construir();
        getMockMvc().perform(delete("/api/equipamentos/"+equipamento.getId() ))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarInvalido() throws Exception{
        Equipamento equipamento = equipamentoBuilder.construir();
        getMockMvc().perform(get("/api/equipamentos/" + ID_INVALIDO))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletarPorIdInvalido() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construir();
        getMockMvc().perform(delete("/api/equipamentos/"+ ID_INVALIDO))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarInvalido() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construirEntidade();
        equipamento.setId(ID_INVALIDO);
        getMockMvc().perform(post("/api/equipamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipamentoBuilder.converterToDto(equipamento))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarNull() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construirEntidade();
        equipamento.setNome(null);
        getMockMvc().perform(post("/api/equipamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipamentoBuilder.converterToDto(equipamento))))
                .andExpect(status().isBadRequest());
    }


}
