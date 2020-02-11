package everis.cad;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
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

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}

	@FeignClient("greeting-client")
	interface Client {
		@RequestMapping(value = "/greeting", method = GET)
		String greeting();
	}
}
