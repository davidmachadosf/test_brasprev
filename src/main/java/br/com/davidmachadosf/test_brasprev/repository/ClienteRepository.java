package br.com.davidmachadosf.test_brasprev.repository;
import static br.com.davidmachadosf.test_brasprev.model.enums.RoleType.VIEW;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.interceptor.annotation.RolesAutorizados;
import br.com.davidmachadosf.test_brasprev.model.Cliente;

@Repository
@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes")
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String> {
	
	@RolesAutorizados({VIEW})
	Cliente findNomeByCpf(@Param("cpf") String cpf);
	
	@RolesAutorizados({VIEW})
	List<Cliente> findByNomeIgnoreCase(@Param("nome") String nome);
	@RolesAutorizados({VIEW})
	List<Cliente> findByNomeContainingIgnoreCaseOrderByNome(@Param("busca") String busca);
    
	@RolesAutorizados({VIEW})
	List<Cliente> findByLogradouroContainingIgnoreCaseOrderByLogradouro(@Param("busca") String busca);
	@RolesAutorizados({VIEW})
	List<Cliente> findByBairroContainingIgnoreCaseOrderByBairro(@Param("busca") String busca);
	@RolesAutorizados({VIEW})
	List<Cliente> findByCidadeContainingIgnoreCaseOrderByCidade(@Param("busca") String busca);
	@RolesAutorizados({VIEW})
	List<Cliente> findByEstadoIgnoreCase(@Param("uf") String uf);
	@RolesAutorizados({VIEW})
	List<Cliente> findByCep(@Param("cep") String cep);

}