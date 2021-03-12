package br.com.davidmachadosf.test_brasprev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

}