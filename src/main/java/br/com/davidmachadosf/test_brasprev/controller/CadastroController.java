package br.com.davidmachadosf.test_brasprev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.davidmachadosf.test_brasprev.model.Cliente;
import br.com.davidmachadosf.test_brasprev.model.Usuario;
import br.com.davidmachadosf.test_brasprev.service.IClienteService;
import br.com.davidmachadosf.test_brasprev.service.IUsuarioService;

@Controller
public class CadastroController {

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/showUsuarios")
    public String findUsuarios(Model model) {

    	List<Usuario> usuarios = (List<Usuario>) usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);

        return "showUsuarios";
    }

    @GetMapping("/showClientes")
    public String findClientes(Model model) {

    	List<Cliente> clientes = (List<Cliente>) clienteService.findAll();

        model.addAttribute("clientes", clientes);

        return "showClientes";
    }
}