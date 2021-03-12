package br.com.davidmachadosf.test_brasprev.service;

import java.util.List;

import br.com.davidmachadosf.test_brasprev.model.Cliente;

public interface IClienteService {

    List<Cliente> findAll();
}