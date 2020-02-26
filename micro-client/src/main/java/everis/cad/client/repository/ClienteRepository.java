package everis.cad.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import everis.cad.client.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
	
}
