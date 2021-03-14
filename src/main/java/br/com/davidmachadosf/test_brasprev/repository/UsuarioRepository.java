package br.com.davidmachadosf.test_brasprev.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.model.Usuario;

@Repository
@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, String> {
	
	Usuario  getByLogin(String login);
	
	List<Usuario> findByRolesContainingIgnoreCase(@Param("role") String role);

}