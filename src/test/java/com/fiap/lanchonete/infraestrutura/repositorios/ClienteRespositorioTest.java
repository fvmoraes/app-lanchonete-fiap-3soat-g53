package com.fiap.lanchonete.infraestrutura.repositorios;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.lanchonete.dominio.Cliente;
import com.fiap.lanchonete.infraestrutura.entidades.ClienteEntity;

@SpringBootTest
public class ClienteRespositorioTest {

    @InjectMocks
    private ClienteRepository clienteRepository;

    @Mock
    private SpringClienteRepository springClienteRepository;


    @Test
    public void testBuscarTodos() {
        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(new ClienteEntity("12345678901", "João"));
        clienteEntities.add(new ClienteEntity("12345678902", "João2"));
        clienteEntities.add(new ClienteEntity("12345678903", "João3"));

when(springClienteRepository.findAll()).thenReturn(clienteEntities);

        List<Cliente> result = clienteRepository.buscarTodos();
        assertEquals(result.get(0).getCpf(), clienteEntities.get(0).getCpf());
		assertEquals(result.get(0).getNome(), clienteEntities.get(0).getNome());
		assertEquals(result.get(1).getCpf(), clienteEntities.get(1).getCpf());
		assertEquals(result.get(1).getNome(), clienteEntities.get(1).getNome());
		assertEquals(result.get(2).getCpf(), clienteEntities.get(2).getCpf());
		assertEquals(result.get(2).getNome(), clienteEntities.get(2).getNome());
    }

    @Test
    public void testSalvar() {
        Cliente cliente = new Cliente("12345678901", "João");
        clienteRepository.salvar(cliente);
    }

    @Test
    public void testBuscarPorCpf() {
        String cpf = "12345678901";
        ClienteEntity clienteEntity = new ClienteEntity(cpf, "João");
        when(springClienteRepository.findByCpf(cpf)).thenReturn(clienteEntity);

        Cliente result = clienteRepository.buscarPorCpf(cpf);
		assertEquals(result.getNome(), clienteEntity.getNome());
		assertEquals(result.getCpf(), clienteEntity.getCpf());
		assertEquals(result.getCpf(), cpf);


    }
}
