package br.com.davidmachadosf.test_brasprev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.davidmachadosf.test_brasprev.model.Usuario;
import br.com.davidmachadosf.test_brasprev.repository.UsuarioRepository;
import utils.PasswordUtils;


@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }
    
    @PatchMapping("/usuarios/alterasenha/{login}/{novaSenha}")
    public Usuario alteraSenha(@PathVariable String login, @PathVariable String novaSenha) {
        Usuario usuario = repository.getByLogin(login);
        usuario.setHash(PasswordUtils.geraHashNovoParaSenha(novaSenha));
        repository.save(usuario);
    	return usuario;
    }
    
    @PatchMapping("/usuarios/verificasenha/{login}/{senha}")
    public Boolean verificaSenha(@PathVariable String login, @PathVariable String senha) {
        Usuario usuario = repository.getByLogin(login);
        String hashAtual = usuario.getHash();
        return PasswordUtils.verificaSenha(hashAtual, senha);
    }
}