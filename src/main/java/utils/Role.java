package utils;

public enum Role {
	
	ADMIN, /* pode pesquisar, cadastrar, altera dados, alterar senhas  e remover usuarios */ 
	EDIT,  /* pode cadastrar, altera dados  e remover clientes */
	VIEW,  /* pode pesquisar clientes cadastrados */
	OWNER; /* pode alterar apenas a própria senha de usuário */

}