package com.src.builder;

import com.src.dominio.Cliente;
import com.src.servico.ClienteServico;
import com.src.servico.dto.ClienteDTO;
import com.src.servico.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Component
public class ClienteBuilder extends ConstrutorDeEntidade<Cliente> {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteServico clienteServico;

    @Override
    public Cliente construirEntidade() throws ParseException {

        Cliente cliente = new Cliente();
        cliente.setNome("Lucas");
        cliente.setTelefone("1234567891011");
        cliente.setRg("1234567");
        cliente.setEndereco("teste");
        cliente.setEmail("lucas@gmail.com");
        cliente.setCpf("56581427098");
        cliente.setDtNasc(LocalDate.of(2000,8,23));

        return cliente;
    }

    @Override
    protected Cliente persistir(Cliente entidade) {
        ClienteDTO clienteDTO = clienteMapper.toDto(entidade);
        ClienteDTO dtoSalvo = clienteServico.adicionar(clienteDTO);
        return clienteMapper.toEntity(dtoSalvo);
    }

    @Override
    protected Collection<Cliente> obterTodos() {
        List<ClienteDTO> clientesDTO = clienteServico.listar();
        return clienteMapper.toEntity(clientesDTO) ;
    }

    @Override
    protected Cliente obterPorId(Integer id) {
        ClienteDTO cliente = clienteServico.buscar(id);
        return clienteMapper.toEntity(cliente);
    }

    protected void deletarPorId(Integer id){
        clienteServico.remover(id);
    }

    public void cleanBank(){
        List<ClienteDTO> clientesDTO = clienteServico.listar();
        clientesDTO.forEach(clienteDTO -> clienteServico.remover(clienteDTO.getId()));
    }

    public ClienteDTO converterToDto(Cliente cliente){
        return clienteMapper.toDto(cliente);
    }


}
