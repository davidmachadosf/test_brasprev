package br.com.davidmachadosf.test_brasprev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication {

	/* primeiro deploy no docker */
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
