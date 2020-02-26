package everis.cad.client.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import everis.cad.client.entity.Cliente;
import everis.cad.client.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public void create(Cliente cliente) {
		repository.save(cliente);
	}

	@Override
	public Optional<Cliente> find(String dni) {
		Optional<Cliente> client = repository.findById(dni);
		if(client.isPresent())
			return client;
		return null;
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}

	@Override
	public void remove(String dni) {
		repository.deleteById(dni);
	}

}
