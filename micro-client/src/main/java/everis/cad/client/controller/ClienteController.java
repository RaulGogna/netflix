package everis.cad.client.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import everis.cad.client.dto.ClienteDto;
import everis.cad.client.entity.Cliente;
import everis.cad.client.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Autowired
	private ModelMapper model;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody ClienteDto dto) {
		if(!service.find(dto.getDni()).get().equals(null))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			service.create(model.map(dto, Cliente.class));
			return new ResponseEntity<>(HttpStatus.ACCEPTED);			
		}
	}

	@GetMapping(value = "/{dni}")
	public ResponseEntity<ClienteDto> find(@PathVariable String dni) {
		Optional<Cliente> cliente = service.find(dni);
		if(cliente.equals(null))
			return new ResponseEntity<ClienteDto>(HttpStatus.NOT_FOUND);
		ClienteDto dto = model.map(cliente.get(), ClienteDto.class);
		return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/c")
	public List<ClienteDto> findAll() {
		List<ClienteDto> clientes = service.findAll()
				.stream()
				.map(c -> model.map(c, ClienteDto.class))
				.collect(Collectors.toList());
		
		return clientes;
	}

	@PutMapping(value = "/{dni}")
	public void update(@RequestBody ClienteDto dto) {
		Cliente cliente = model.map(dto, Cliente.class);
		service.create(cliente);
	}

	@DeleteMapping("/{dni}")
	public void remove(@PathVariable String dni) {
		service.remove(dni);
	}
}
