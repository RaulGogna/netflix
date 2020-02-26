package everis.cad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import everis.cad.client.Client;
import everis.cad.dto.ClienteDto;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableAutoConfiguration
@EnableFeignClients
public class FeignClientApplication {
	
    @Value("${spring.application.name}")
    private String appName;
	
	@Autowired
	Client client;

	@RequestMapping("/greeting")
	public String getGreeting() {
		return client.greeting();	
	}
	
	@PostMapping
	public ResponseEntity<String> getCreate(@RequestBody ClienteDto dto){
		return client.create(dto);
	}
	
	@GetMapping(value = "/{dni}")
	public ResponseEntity<ClienteDto> find(@PathVariable String dni){
		return client.find(dni);
	}

	@GetMapping
	public List<ClienteDto> findAll(){
		return client.findAll();
	}
	
	@PutMapping(value = "/{dni}")
	public void update(@RequestBody ClienteDto dto) {
		client.update(dto);
	}
	
	@DeleteMapping("/{dni}")
	public void remove(@PathVariable String dni) {
		client.remove(dni);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}

	
}
