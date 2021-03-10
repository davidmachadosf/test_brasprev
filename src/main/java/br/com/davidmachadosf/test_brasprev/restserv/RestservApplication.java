package br.com.davidmachadosf.test_brasprev.restserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestservApplication {

	/* primeiro deploy no docker */
	public static void main(String[] args) {
		SpringApplication.run(RestservApplication.class, args);
	}

}
