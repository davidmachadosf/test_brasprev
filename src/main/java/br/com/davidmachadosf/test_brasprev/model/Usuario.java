package br.com.davidmachadosf.test_brasprev.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb01_usuarios")
@Data
public class Usuario {
	
    @Id
    String login;  // login do usuário (chave)
	String hash;   // hash da senha de usuário
    int nivel;     // nivel de acesso (0:admin)
}
