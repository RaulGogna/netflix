package everis.cad.client.service;

import java.util.List;
import java.util.Optional;

import everis.cad.client.entity.Cliente;

public interface ClienteService {

	void create(Cliente cliente);

	Optional<Cliente> find(String dni);

	List<Cliente> findAll();

	void remove(String dni);

}
