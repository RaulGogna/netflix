package everis.cad.client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import everis.cad.client.dto.ClienteDto;

public interface ClientController {

	ResponseEntity<String> create(ClienteDto dto);

	ResponseEntity<ClienteDto> find(String dni);

	List<ClienteDto> findAll();

	void update(ClienteDto dto);

	void remove(String dni);

}