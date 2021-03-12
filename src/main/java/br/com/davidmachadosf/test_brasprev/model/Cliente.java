package br.com.davidmachadosf.test_brasprev.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb02_clientes")
public class Cliente {
	
    @Id
    private String cpf;
	private String nome;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    
    public Cliente() {};
    
	public Cliente(String cpf, String nome, String logradouro, String bairro, String cidade, String estado, String cep) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairo(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}
    

}
