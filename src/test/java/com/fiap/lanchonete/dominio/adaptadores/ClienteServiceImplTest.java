package com.fiap.lanchonete.dominio.adaptadores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.lanchonete.dominio.Cliente;
import com.fiap.lanchonete.dominio.adaptadores.servicos.ClienteServiceImpl;
import com.fiap.lanchonete.dominio.dtos.ClienteDto;
import com.fiap.lanchonete.dominio.exceptions.ClientJaCadastradoException;
import com.fiap.lanchonete.dominio.exceptions.ClientNaoEncontradoException;
import com.fiap.lanchonete.dominio.portas.repositorios.ClienteRepositoryPort;

@SpringBootTest
public class ClienteServiceImplTest {

	@InjectMocks
	private ClienteServiceImpl clienteService;

	@Mock
	private ClienteRepositoryPort clienteRepository;

	@Test
	public void testBuscarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente("12345678901", "João"));
		clientes.add(new Cliente("12345678902", "João2"));
		clientes.add(new Cliente("12345678903", "João3"));

		when(clienteRepository.buscarTodos()).thenReturn(clientes);

		List<ClienteDto> result = clienteService.buscarClientes();
		
		assertEquals(result.get(0).getCpf(), clientes.get(0).getCpf());
		assertEquals(result.get(0).getNome(), clientes.get(0).getNome());
		assertEquals(result.get(1).getCpf(), clientes.get(1).getCpf());
		assertEquals(result.get(1).getNome(), clientes.get(1).getNome());
		assertEquals(result.get(2).getCpf(), clientes.get(2).getCpf());
		assertEquals(result.get(2).getNome(), clientes.get(2).getNome());
	}

	@Test
	public void testCriaClienteComSucesso() throws ClientJaCadastradoException {
		ClienteDto clienteDto = new ClienteDto("12345678901", "João");
		when(clienteRepository.buscarPorCpf("12345678901")).thenReturn(null);
		clienteService.CriaCliente(clienteDto);
		
	}

	@Test
	public void testCriaClienteJaCadastrado() throws ClientJaCadastradoException {
		ClienteDto clienteDto = new ClienteDto("12345678901", "João");
		when(clienteRepository.buscarPorCpf("12345678901")).thenReturn(new Cliente("12345678901", "João"));

		assertThrows(ClientJaCadastradoException.class, () -> clienteService.CriaCliente(clienteDto));
	}

	@Test
	public void testAtualizaClienteComSucesso() throws ClientNaoEncontradoException {
		ClienteDto clienteDto = new ClienteDto("12345678901", "Joao2");
		when(clienteRepository.buscarPorCpf("12345678901")).thenReturn(new Cliente("12345678901", "Joao"));

		clienteService.AtualizaCliente(clienteDto);
		var client = clienteService.buscaClienteCpf("12345678901");
		assertEquals(client.getNome(), clienteDto.getNome());
		// Verifique se o método salvar no repositório foi chamado
	}

	@Test
	public void testAtualizaClienteNaoEncontrado() throws ClientNaoEncontradoException {
		ClienteDto clienteDto = new ClienteDto("12345678901", "João");
		when(clienteRepository.buscarPorCpf("12345678901")).thenReturn(null);

		assertThrows(ClientNaoEncontradoException.class,() -> clienteService.AtualizaCliente(clienteDto)) ;
	}

	@Test
	public void testBuscaClienteCpfComSucesso() {
		Cliente cliente = new Cliente("12345678901", "João");
		when(clienteRepository.buscarPorCpf("12345678901")).thenReturn(cliente);

		ClienteDto result = clienteService.buscaClienteCpf("12345678901");
		
		assertEquals(result.getCpf(), cliente.getCpf());
		assertEquals(result.getNome(), cliente.getNome());

	}

@Test
public void testBuscaClienteCpfNaoEncontrado() {
    when(clienteRepository.buscarPorCpf("12345678901")).thenReturn(null);

    ClienteDto result = clienteService.buscaClienteCpf("12345678901");
    assertNull(result);
}
}
