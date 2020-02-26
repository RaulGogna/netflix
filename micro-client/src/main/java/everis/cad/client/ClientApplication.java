package everis.cad.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

import everis.cad.client.controller.ClientController;
import everis.cad.client.controller.ClienteController;
import everis.cad.client.dto.ClienteDto;

@SpringBootApplication
@RestController
@EnableJpaRepositories
public class ClientApplication implements ClientController {
 
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
 
    @Value("${spring.application.name}")
    private String appName;
 
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
 
//    @Override
//    public String greeting() {
//        return String.format(
//          "Hello from '%s'!", eurekaClient.getApplication(appName).getName());
//    }
    @Autowired
	private ClienteController controller;

	@Override
	@PostMapping
	public ResponseEntity<String> create(@RequestBody ClienteDto dto) {
		return controller.create(dto);
	}

	@Override
	@GetMapping(value = "/{dni}")
	public ResponseEntity<ClienteDto> find(@PathVariable String dni) {
		return controller.find(dni);
	}

	@Override
	@GetMapping()
	public List<ClienteDto> findAll() {
		return controller.findAll();
	}

	@Override
	@PutMapping(value = "/{dni}")
	public void update(@RequestBody ClienteDto dto) {
		controller.update(dto);
	}

	@Override
	@DeleteMapping("/{dni}")
	public void remove(@PathVariable String dni) {
		controller.remove(dni);
	}
}