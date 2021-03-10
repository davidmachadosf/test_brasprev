package br.com.davidmachadosf.test_brasprev.restserv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="")
public class IndexController {

	@GetMapping
	@RequestMapping(value ="/hello")
	public String hello() {
    	return "Hello world!! :-D";
	}
	
	@GetMapping
	@RequestMapping(value ="/bye")
	public String bye() {
    	return "Bye bye cruel world... :-(";
	}

}