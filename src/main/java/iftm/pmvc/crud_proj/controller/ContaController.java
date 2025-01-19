package iftm.pmvc.crud_proj.controller;

import iftm.pmvc.crud_proj.domain.Cliente;
import iftm.pmvc.crud_proj.domain.Conta;
import iftm.pmvc.crud_proj.repository.ClienteRepository;
import iftm.pmvc.crud_proj.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/conta")
    public String listar(Model model) {
        List<Conta> contas = contaRepository.listar();
        model.addAttribute("contas", contas);
        return "conta/listarConta";
    }

    @GetMapping("/conta/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        Conta contaBusca = contaRepository.buscarPorId(id);
        if (contaBusca == null) {
            model.addAttribute("mensagem", "Conta com ID " + id + " não encontrada.");
            return "conta/listarConta";
        }
        model.addAttribute("conta", contaBusca);
        return "conta/listarConta";
    }

    @GetMapping("/conta/novo")
    public String novaConta(Model model) {
        Conta conta = new Conta();
        model.addAttribute("conta", conta);
        return "conta/cadastrarConta";
    }

    @PostMapping("/conta/salvar")
    public String salvar(@RequestParam("clienteId") Integer clienteId, @Validated @ModelAttribute Conta conta, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "conta/cadastrarConta"; 
        }
        Cliente cliente = clienteRepository.buscarPorId(clienteId);
        if (cliente == null) {
            result.rejectValue("cliente", "error.conta", "Cliente não encontrado");
            return "conta/cadastrarConta";
        }
        conta.setCliente(cliente);
        contaRepository.adicionar(conta);
        attributes.addFlashAttribute("mensagem", "Conta salva com sucesso!");
        return "redirect:/conta";
    }
}