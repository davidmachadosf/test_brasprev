package br.com.davidmachadosf.test_brasprev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    	model.addAttribute("usuarios", usuarioService.findAll());
        return "showUsuarios";
    }

    @GetMapping("/showClientes")
    public String findClientes(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "showClientes";
    }
}