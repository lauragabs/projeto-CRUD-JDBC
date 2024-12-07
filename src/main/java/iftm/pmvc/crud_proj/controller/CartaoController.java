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

import iftm.pmvc.crud_proj.domain.Cartao; 
import iftm.pmvc.crud_proj.repository.CartaoRepository; 

@Controller
@RequestMapping("/cartao") 
public class CartaoController { 

    private final CartaoRepository cartaoRepository; 

    @Autowired
    public CartaoController(CartaoRepository cartaoRepository) { 
        this.cartaoRepository = cartaoRepository; 
    }

    public static final String URL_LISTAR = "cartao/listarCartao"; 
    public static final String URL_CADASTRAR = "cartao/cadastrarCartao"; 
    public static final String URL_RED_LISTAR = "redirect:/cartao"; 

    @GetMapping
    public String listar(Model model) {
        List<Cartao> cartoes = cartaoRepository.listar(); 
        model.addAttribute("cartoes", cartoes); 
        return URL_LISTAR;
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        Cartao cartaoBusca = cartaoRepository.buscarPorId(id); 
        if (cartaoBusca == null) {
            model.addAttribute("mensagem", "Cartão com ID " + id + " não encontrado."); 
            return URL_LISTAR;
        }
        model.addAttribute("cartao", cartaoBusca); 
        return URL_LISTAR;
    }

    @GetMapping("/novo")
    public String novoCartao(Model model){ 
        Cartao cartao = new Cartao(); 
        model.addAttribute("cartao", cartao); 
        return URL_CADASTRAR;
    }

    @GetMapping("/editar/{id}")
    public String editarCartao(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) { 
        Cartao cartaoBusca = cartaoRepository.buscarPorId(id); 
        if (cartaoBusca == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Cartão com ID " + id + " não encontrado"); 
            return URL_RED_LISTAR;
        }
        model.addAttribute("cartao", cartaoBusca); 
        return URL_CADASTRAR;
    }    

    @PostMapping("/novo")
    public String salvar(@ModelAttribute("cartao") Cartao cartao, RedirectAttributes redirectAttributes){ 
        cartaoRepository.adicionar(cartao); 
        redirectAttributes.addFlashAttribute("mensagem", cartao.getId() + " Salvo com sucesso"); 
        return URL_RED_LISTAR;
    }

    @PostMapping(value = "/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        cartaoRepository.deletar(id); 
        redirectAttributes.addFlashAttribute("mensagem", id + " Excluído com sucesso");
        return URL_RED_LISTAR;
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable("id") Integer id, @ModelAttribute("cartao") Cartao cartao, RedirectAttributes redirectAttributes) { 
        if (cartaoRepository.update(cartao)) { 
            redirectAttributes.addFlashAttribute("mensagem", cartao.getId() + " atualizado com sucesso"); 
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Não foi possível atualizar " + cartao.getId()); 
        }
        return URL_RED_LISTAR;
    }
}