package everis.cad.client;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import everis.cad.dto.ClienteDto;

@FeignClient("micro-client")
public interface Client {
		
		@PostMapping
		ResponseEntity<String> create(ClienteDto dto);

		@GetMapping(value = "/{dni}")
		ResponseEntity<ClienteDto> find(String dni);

		@GetMapping
		List<ClienteDto> findAll();

		@PutMapping(value = "/{dni}")
		void update(ClienteDto dto);

		@DeleteMapping("/{dni}")
		void remove(String dni);
		
		@RequestMapping(value = "/greeting", method = GET)
		String greeting();
}
