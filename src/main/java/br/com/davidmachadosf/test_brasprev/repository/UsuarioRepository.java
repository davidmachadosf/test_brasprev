package br.com.davidmachadosf.test_brasprev.repository;

import static br.com.davidmachadosf.test_brasprev.model.enums.RoleType.ADMIN;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.interceptor.annotation.RolesAutorizados;
import br.com.davidmachadosf.test_brasprev.model.Usuario;

@Repository
@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, String> {
	
	
	@RolesAutorizados({ADMIN})
	Usuario  getByLogin(String login);
	
	@RolesAutorizados({ADMIN})
	List<Usuario> findByRolesContainingIgnoreCase(@Param("role") String role);

}