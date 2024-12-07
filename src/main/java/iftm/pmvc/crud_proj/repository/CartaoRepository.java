package iftm.pmvc.crud_proj.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import iftm.pmvc.crud_proj.domain.Cartao; 
import iftm.pmvc.crud_proj.domain.Conta;
import jakarta.annotation.PostConstruct;

@Repository
public class CartaoRepository { 

    private List<Cartao> cartoes; 

    @Autowired
    private ContaRepository contaRepository;

    public CartaoRepository() {
        this.cartoes = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        Conta conta0 = contaRepository.buscarPorId(0); 
        Conta conta1 = contaRepository.buscarPorId(1);

        this.cartoes.add(new Cartao(0, conta0, "1234-5678-9012-3456", LocalDate.of(2025, 12, 31), 5000.00f, "Crédito", 123));
        this.cartoes.add(new Cartao(1, conta1, "9876-5432-1098-7654", LocalDate.of(2026, 11, 30), 3000.00f, "Débito", 456));
    }

    public List<Cartao> listar() {
        return this.cartoes; 
    }

    public Cartao buscarPorId(Integer id) {
        for (Cartao cartao : this.cartoes) {
            if (cartao.getId().equals(id)) { 
                return cartao; 
            }
        }
        return null; 
    }

    public void adicionar(Cartao cartao) {
        cartoes.add(cartao); 
    }

    public void deletar(Integer id) {
        Cartao cartaoParaRemover = buscarPorId(id);
        if (cartaoParaRemover != null) {
            cartoes.remove(cartaoParaRemover); 
        }
    }

    public boolean update(Cartao cartao) {
        for (int i = 0; i < cartoes.size(); i++) {
            Cartao cartaoExistente = cartoes.get(i);
            if (cartaoExistente.getId().equals(cartao.getId())) { 
                cartoes.set(i, cartao); 
                return true;
            }
        }
        return false;
    }
}