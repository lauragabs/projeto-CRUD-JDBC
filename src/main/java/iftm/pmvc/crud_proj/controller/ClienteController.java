package iftm.pmvc.crud_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import iftm.pmvc.crud_proj.domain.Cliente;
import iftm.pmvc.crud_proj.repository.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public static final String URL_LISTAR = "cliente/listar";
    public static final String URL_CADASTRAR = "cliente/cadastrar";
    public static final String URL_RED_LISTAR = "redirect:/cliente";

    @GetMapping
    public String listar(Model model) {
        List<Cliente> clientes = clienteRepository.listar();
        model.addAttribute("clientes", clientes);
        return URL_LISTAR;
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        Cliente clienteBusca = clienteRepository.buscarPorId(id);
        if (clienteBusca == null) {
            model.addAttribute("mensagem", "Cliente com ID " + id + " não encontrado.");
            return URL_LISTAR;
        }
        model.addAttribute("clientes", List.of(clienteBusca));
        return URL_LISTAR;
    }


    @GetMapping("/novo")
    public String novoCliente(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return URL_CADASTRAR;
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Cliente clienteBusca = clienteRepository.buscarPorId(id);
        if (clienteBusca == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Cliente com ID " + id + " não encontrado");
            return URL_RED_LISTAR;
        }
        model.addAttribute("cliente", clienteBusca);
        return URL_CADASTRAR;
    }    


    @PostMapping("/novo")
    public String salvar(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes){
        clienteRepository.adicionar(cliente);
        redirectAttributes.addFlashAttribute("mensagem", cliente.getNome()+" Salvo com sucesso");
        return URL_RED_LISTAR;
    }

    @PostMapping(value = "/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        clienteRepository.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", id+ " Excluido com sucesso");
        return URL_RED_LISTAR;
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Integer id, @ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes) {
        if (clienteRepository.update(cliente)) {
            redirectAttributes.addFlashAttribute("mensagem", cliente.getNome() + " atualizado com sucesso");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Não foi possível atualizar " + cliente.getNome());
        }
        return URL_RED_LISTAR;
    }
    
  
}
