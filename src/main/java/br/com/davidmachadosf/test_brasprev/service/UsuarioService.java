package br.com.davidmachadosf.test_brasprev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import br.com.davidmachadosf.test_brasprev.model.Usuario;
import br.com.davidmachadosf.test_brasprev.repository.UsuarioRepository;

@Service
@RestController
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    @Override    
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }
    
}