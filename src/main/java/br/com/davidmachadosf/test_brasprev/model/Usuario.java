package br.com.davidmachadosf.test_brasprev.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb01_usuarios")
public class Usuario {
	
    @Id
    private String login;  // login do usuário (chave)
	private String hash;   // hash da senha de usuário
    private int nivel;     // nivel de acesso (0:admin)

    public Usuario() {};
    
    public Usuario(String login, String hash, int nivel) {
		super();
		this.login = login;
		this.hash  = hash;
		this.nivel = nivel;
	}

	
    public String getLogin() {
		return login;
	}

    public void setLogin(String login) {
		this.login = login;
	}


	public String getHash() {
		return hash;
	}

	
	public void setHashpasswd(String hash) {
		this.hash = hash;
	}


	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
