package br.com.davidmachadosf.test_brasprev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davidmachadosf.test_brasprev.model.Cliente;
import br.com.davidmachadosf.test_brasprev.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> findAll() {

        return (List<Cliente>) repository.findAll();

    }
}