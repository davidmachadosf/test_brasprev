package br.com.davidmachadosf.test_brasprev.service;

import java.util.List;

import br.com.davidmachadosf.test_brasprev.model.Usuario;

public interface IUsuarioService {

    List<Usuario> findAll();
}