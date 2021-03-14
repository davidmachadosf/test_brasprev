package br.com.davidmachadosf.test_brasprev.service;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.davidmachadosf.test_brasprev.model.Usuario;
import br.com.davidmachadosf.test_brasprev.repository.UsuarioRepository;
import utils.PasswordUtils;

@Service
@RestController
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }
    
    @GetMapping("/auth/{login}/{senha}")
    public ResponseEntity<String> auth(@PathVariable String login, @PathVariable String senha) {
    	try{
	        if(PasswordUtils.verificaSenha(getHash(login), senha)) {    	
	        	return ok().body(PasswordUtils.geraToken(login, senha));
			} 
    	}
    	catch (Exception ex) {
		    return status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}        
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Autenticação inválida![0]");
    }

	

	@PatchMapping("/alterasenha/{login}/{novaSenha}")
    public ResponseEntity<String> alteraSenha(@PathVariable String login, @PathVariable String novaSenha) {        
        try {
            Usuario usuario = getUsuario(login);
            usuario.setHash(PasswordUtils.geraHashNovoParaSenha(novaSenha));
            repository.save(usuario);        	
			return ok().body("Senha alterada.");
		} 
    	catch (Exception ex) {
    		return status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}        
    }
    
    @GetMapping("/verificasenha/{login}/{senha}")
    public ResponseEntity<String> verificaSenha(@PathVariable String login, @PathVariable String senha) {
        try {
            return ok().body(""+PasswordUtils.verificaSenha(getHash(login), senha));
		} 
    	catch (Exception ex) {
    		return status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
    }
    
    private Usuario getUsuario(String login) throws Exception {
		Usuario usuario = repository.getByLogin(login);
  		if(null==usuario) {
			throw new Exception("Usuário inexistente!");
		}
		return usuario;
	}
    
    private String getHash(String login) throws Exception {
		return getUsuario(login).getHash();
	}
}