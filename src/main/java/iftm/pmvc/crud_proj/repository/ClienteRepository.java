package iftm.pmvc.crud_proj.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import iftm.pmvc.crud_proj.domain.Cliente;

@Repository
public class ClienteRepository {

    private List<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();

        this.clientes.add(new Cliente(0, "Joana da Silva", "12345678901", "11912347895", "Rua 1, 33 , São Paulo-SP", "jojo@gmail.com", "Jojo1515", "Janela123", "Feminino", LocalDate.of(1990, 5, 25)));
        this.clientes.add(new Cliente(1, "Carlos Pereira", "98765432100", "31998765432", "Avenida Principal, 123, Belo Horizonte-MG", "carlosp@gmail.com", "Carlos123", "SenhaCarlos", "Masculino", LocalDate.of(1985, 11, 15)));
        
    }

    public List<Cliente> listar() {
        return this.clientes;
    }

    // Buscar por um cliente único
    public Cliente buscarPorId(Integer id) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getId() == id) {
                return cliente; 
            }
        }
        return null; 
    }

    // Buscar por nome
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clienteBusca = new ArrayList<>();
        for (Cliente cliente : this.clientes) {
            if (cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
                clienteBusca.add(cliente);
            }
        }
        return clienteBusca;
    }

    //Buscar por login
    public Cliente buscarPorLogin(String login) {
        for (Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login)) { 
                return cliente; 
            }
        }
        return null;
    }

    //Adicionar um novo cliente
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    //deletar um cliente
    public void deletar(Integer id) {
        Cliente clienteParaRemover = buscarPorId(id);
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
        }
    }

    // atualizar um cliente
    public boolean update(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente clienteExistente = clientes.get(i);
            if (clienteExistente.getId() == cliente.getId()) {
                clientes.set(i, cliente); 
                return true;
            }
        }
        return false;
    }
    

}
