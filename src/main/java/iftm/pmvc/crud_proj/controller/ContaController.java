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

import iftm.pmvc.crud_proj.domain.Conta; 
import iftm.pmvc.crud_proj.repository.ContaRepository; 

@Controller
@RequestMapping("/conta") 
public class ContaController { 

    private final ContaRepository contaRepository; 

    @Autowired
    public ContaController(ContaRepository contaRepository) { 
        this.contaRepository = contaRepository; 
    }

    public static final String URL_LISTAR = "conta/listarConta"; 
    public static final String URL_CADASTRAR = "conta/cadastrarConta"; 
    public static final String URL_RED_LISTAR = "redirect:/conta"; 

    @GetMapping
    public String listar(Model model) {
        List<Conta> contas = contaRepository.listar(); 
        model.addAttribute("contas", contas); 
        return URL_LISTAR;
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        Conta contaBusca = contaRepository.buscarPorId(id); 
        if (contaBusca == null) {
            model.addAttribute("mensagem", "Conta com ID " + id + " não encontrada."); 
            return URL_LISTAR;
        }
        model.addAttribute("conta", contaBusca);
        return URL_LISTAR;
    }

    @GetMapping("/novo")
    public String novaConta(Model model){ 
        Conta conta = new Conta(); 
        model.addAttribute("conta", conta); 
        return URL_CADASTRAR;
    }

    @GetMapping("/editar/{id}")
    public String editarConta(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) { 
        Conta contaBusca = contaRepository.buscarPorId(id); 
        if (contaBusca == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Conta com ID " + id + " não encontrada"); 
            return URL_RED_LISTAR;
        }
        model.addAttribute("conta", contaBusca); 
        return URL_CADASTRAR;
    }    

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("conta") Conta conta, RedirectAttributes redirectAttributes){ 
        contaRepository.adicionar(conta); 
        redirectAttributes.addFlashAttribute("mensagem", conta.getId()+" Salvo com sucesso");
        return URL_RED_LISTAR;
    }

    @PostMapping(value = "/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        contaRepository.deletar(id); 
        redirectAttributes.addFlashAttribute("mensagem", id+ " Excluido com sucesso");
        return URL_RED_LISTAR;
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Integer id, @ModelAttribute("conta") Conta conta, RedirectAttributes redirectAttributes) { 
        if (contaRepository.update(conta)) { 
            redirectAttributes.addFlashAttribute("mensagem", conta.getId() + " atualizado com sucesso"); 
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Não foi possível atualizar " + conta.getId()); 
        }
        return URL_RED_LISTAR;
    }
    
  
} 