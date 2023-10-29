package com.fiap.lanchonete.adaptadores.controllers;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fiap.lanchonete.aplicacao.adaptadores.controllers.ClienteController;
import com.fiap.lanchonete.dominio.dtos.ClienteDto;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.interfaces.ClienteServicePort;

@SpringBootTest
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteServicePort clienteService;

    @Test
    public void testCriarClienteComSucesso() throws ClientJaCadastradoException {
        ClienteDto clienteDto = new ClienteDto("012.153.123-12", "Teste Sample");
        Mockito.doNothing().when(clienteService).CriaCliente(clienteDto);
        ResponseEntity<String> responseEntity = clienteController.criarCliente(clienteDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Cliente cadastrado com sucesso", responseEntity.getBody());
    }

    @Test
    public void testCriarClienteExistente() throws ClientJaCadastradoException {
        ClienteDto clienteDto = new ClienteDto("012.153.123-12", "Teste Sample");
        Mockito.doThrow(ClientJaCadastradoException.class).when(clienteService).CriaCliente(clienteDto);
        ResponseEntity<String> responseEntity = clienteController.criarCliente(clienteDto);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Cliente já cadastrado", responseEntity.getBody());
    }

    @Test
    public void testBuscarClientesComSucesso() {
        List<ClienteDto> clientes = new ArrayList<>(); // Crie uma lista de clientes de exemplo
        when(clienteService.buscarClientes()).thenReturn(clientes);

        List<ClienteDto> result = clienteController.buscarClientes();
        assertEquals(clientes, result);
    }

    
    @Test
    public void testBuscaClienteCpfComSucesso() throws ClientNaoEncontradoException {
        String cpf = "12345678901"; // CPF válido de exemplo
        ClienteDto cliente = new ClienteDto("012.153.123-12", "Teste Sample");
        when(clienteService.buscaClienteCpf(cpf)).thenReturn(cliente);
        ClienteDto result = clienteController.buscaClienteCpf(cpf);
        assertEquals(cliente, result);
    }

    @Test
    public void testBuscaClienteCpfNaoEncontrado() throws ClientNaoEncontradoException {
        String cpf = "12345678901"; // CPF válido de exemplo

        var client = clienteController.buscaClienteCpf(cpf);
        assertEquals(client,null);
    }
    
    @Test
    public void testAtualizaClienteComSucesso() throws ClientNaoEncontradoException {
        ClienteDto cliente = new ClienteDto("012.153.123-12", "Teste Sample");
        doNothing().when(clienteService).AtualizaCliente(cliente);

        ResponseEntity<String> responseEntity = clienteController.atualizaClientes(cliente);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Cliente atualizado com sucesso", responseEntity.getBody());
    }

    @Test
    public void testAtualizaClienteNaoEncontrado() throws ClientNaoEncontradoException {
        ClienteDto cliente = new ClienteDto("012.153.123-12", "Teste Sample");
        doThrow(ClientNaoEncontradoException.class).when(clienteService).AtualizaCliente(cliente);

        ResponseEntity<String> responseEntity = clienteController.atualizaClientes(cliente);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Cliente não cadastrado", responseEntity.getBody());
    }

}
