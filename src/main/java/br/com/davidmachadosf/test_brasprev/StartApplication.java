package br.com.davidmachadosf.test_brasprev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import br.com.davidmachadosf.test_brasprev.model.Cliente;
import br.com.davidmachadosf.test_brasprev.model.Usuario;

@SpringBootApplication
public class StartApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	
	// configura JPA Data REST para exibir os ids das entidades nos responses
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Usuario.class, Cliente.class);
    }

}