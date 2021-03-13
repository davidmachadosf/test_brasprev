package br.com.davidmachadosf.test_brasprev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.davidmachadosf.test_brasprev.model.Cliente;

@Repository
@RepositoryRestResource(collectionResourceRel = "cliente", path = "clientes")
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String> {
// obs: PagingAndSortingRepository<Cliente, String> entends CrudRepository<Cliente, String> { 
//public interface ClienteRepository extends  
	
	/**
     * Método que retorna uma lista de clientes fazendo a busca pelo nome
     passado como parâmetro.
     *
     * @param name
     * @return lista de clientes
     */
    List<Cliente> findByNome(@Param("nome") String nome);

    /**
     * Método que retorna o nome do cliente fazendo a busca
     com o cpf passado como parâmetro.
     *
     * @param id
     * @return cliente do id passado como parâmetro.
     */
    @Query("SELECT c.nome FROM Cliente c where c.cpf = :cpf")
  Cliente findNomeByCpf(@Param("cpf") String cpf);

    /**
     * Método que retorna uma lista de clientes fazendo a busca pelo nome passado
     como parâmetro e ordenando os clientes pelo nome.
     *
     * @param nome
     * @return lista de clientes
     */
    List<Cliente> findByNomeOrderByNome(@Param("nome") String nome);
    
    List<Cliente> findByNomeContainingOrderByNomeDesc(@Param("nome") String nome);

}