package com.src.servico;

import com.src.dominio.Cliente;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.excecao.RegraNegocioException;
import com.src.servico.mapper.ClienteMapper;
import com.src.repositorio.ClienteRepositorio;
import com.src.servico.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ClienteServico {

    private final ClienteRepositorio clienteRepositorio;
    private final ClienteMapper clienteMapper;
    private final ReservaRepositorio reservaRepositorioo;

    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<ClienteDTO> clientesDto = clienteMapper.toDto(clientes);
        return clientesDto;
    }

    public ClienteDTO buscar(Integer id) {
        Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));
        ClienteDTO clienteDto = clienteMapper.toDto(cliente);
        return clienteDto;
    }

    public ClienteDTO adicionar(ClienteDTO clienteDto) {
        validarCliente(clienteDto);
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        Cliente clienteSalvo = clienteRepositorio.save(cliente);
        return clienteMapper.toDto(clienteSalvo);

    }

    public void remover(Integer id) {
        if (existReserva(clienteMapper.toEntity(buscar(id))))
            throw new RegraNegocioException("Cliente não pode ser removido, pois possui reservas cadastradas.");
        verificaRepeticao(id);
        clienteRepositorio.deleteById(id);
    }

    private void verificaRepeticao(Integer id) {
        if (!clienteRepositorio.existsById(id))
            throw new RegraNegocioException("Id não encontrado!");
    }

    private boolean isNumber(String string) {
        try {
            Long numero = Long.parseLong(string);
            return true;
        } catch (Exception E) {
            return false;
        }
    }
    

    private boolean existReserva(Cliente cliente) {
        return reservaRepositorioo.existsByCliente(cliente);
    }

    private void validarCliente(ClienteDTO clienteDto) {
        if (clienteDto.getId() != null) {
            verificaRepeticao(clienteDto.getId());
        } else if (clienteRepositorio.existsByCpf(clienteDto.getCpf()))
            throw new RegraNegocioException("Cpf cadastrado no sistema!");
        else if (clienteRepositorio.existsByRg(clienteDto.getRg()))
            throw new RegraNegocioException("Rg cadastrado no sistema!");
        else if (clienteRepositorio.existsByEmail(clienteDto.getEmail()))
            throw new RegraNegocioException("Email cadastrado no sistema!");
        else if (!isNumber(clienteDto.getRg()))
            throw new RegraNegocioException("O campo Rg só pode conter números!");
        else if (!isNumber(clienteDto.getCpf()))
            throw new RegraNegocioException("O campo Cpf só pode conter números!");
    }
}
