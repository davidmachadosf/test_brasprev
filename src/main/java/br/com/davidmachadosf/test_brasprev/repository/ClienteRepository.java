package br.com.davidmachadosf.test_brasprev.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.model.Cliente;

@Repository
@RepositoryRestResource(collectionResourceRel = "cliente", path = "clientes")
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String> {
// obs: PagingAndSortingRepository<Cliente, String> entends CrudRepository<Cliente, String> { 
//public interface ClienteRepository extends  
}