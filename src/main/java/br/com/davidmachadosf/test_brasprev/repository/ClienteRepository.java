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
	
	Cliente findNomeByCpf(@Param("cpf") String cpf);
	
	List<Cliente> findByNomeIgnoreCase(@Param("nome") String nome);
	List<Cliente> findByNomeContainingIgnoreCaseOrderByNome(@Param("busca") String busca);
    
	List<Cliente> findByLogradouroContainingIgnoreCaseOrderByLogradouro(@Param("busca") String busca);
	List<Cliente> findByBairroContainingIgnoreCaseOrderByBairro(@Param("busca") String busca);
	List<Cliente> findByCidadeContainingIgnoreCaseOrderByCidade(@Param("busca") String busca);
	List<Cliente> findByEstadoIgnoreCase(@Param("uf") String uf);
	List<Cliente> findByCep(@Param("cep") String cep);

}