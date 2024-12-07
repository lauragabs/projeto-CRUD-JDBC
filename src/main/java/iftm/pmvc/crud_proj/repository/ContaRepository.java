package iftm.pmvc.crud_proj.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import iftm.pmvc.crud_proj.domain.Cliente;
import iftm.pmvc.crud_proj.domain.Conta;
import jakarta.annotation.PostConstruct;

@Repository
public class ContaRepository {

    private List<Conta> contas;

    @Autowired
    private ClienteRepository clienteRepository;

    public ContaRepository() {
        this.contas = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        Cliente cliente0 = clienteRepository.buscarPorId(0);
        Cliente cliente1 = clienteRepository.buscarPorId(1);

        this.contas.add(new Conta(0, cliente0, "Corrente", 1524.36f, LocalDate.of(2020, 02, 16)));
        this.contas.add(new Conta(1, cliente1, "Poupança", 3000.00f, LocalDate.of(2021, 05, 10)));
    }

    public List<Conta> listar() {
        return this.contas;
    }

    public Conta buscarPorId(Integer id) {
        for (Conta conta : this.contas) {
            if (conta.getId() == id) {
                return conta; 
            }
        }
        return null; 
    }

    public void adicionar(Conta conta) {
        contas.add(conta);
    }

    public void deletar(Integer id) {
        Conta contaParaRemover = buscarPorId(id);
        if (contaParaRemover != null) {
            contas.remove(contaParaRemover);
        }
    }

    public boolean update(Conta conta) {
        for (int i = 0; i < contas.size(); i++) {
            Conta contaExistente = contas.get(i);
            if (contaExistente.getId() == conta.getId()) {
                contas.set(i, conta); 
                return true;
            }
        }
        return false;
    }
}