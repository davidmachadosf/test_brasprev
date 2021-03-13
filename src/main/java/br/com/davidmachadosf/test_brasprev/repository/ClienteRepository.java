package br.com.davidmachadosf.test_brasprev.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.model.Cliente;

@Repository
@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes")
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String> {
	
	String findNomeByCpf(@Param("cpf") String cpf);
	
	List<Cliente> findByNome(@Param("nome") String nome);
    List<Cliente> findByNomeContainingOrderByNome(@Param("busca") String busca);
    
	List<Cliente> findByLogradouroContainingOrderByLogragouro(@Param("busca") String busca);
	List<Cliente> findByBairroContainingOrderByBairro(@Param("busca") String busca);
	List<Cliente> findByCidadeContainingOrderByCidade(@Param("busca") String busca);
	List<Cliente> findByEstadoIgnoreCaseOrderByEstado(@Param("uf") String uf);
	List<Cliente> findByCepOrderByCep(@Param("cep") String cep);

}