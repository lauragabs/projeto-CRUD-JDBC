package iftm.pmvc.crud_proj.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iftm.pmvc.crud_proj.domain.Cliente;

@Repository
public class ClienteRepository {

    private JdbcTemplate conexaoBanco;

    public ClienteRepository(JdbcTemplate conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    public List<Cliente> listar() {
        String sql = "SELECT id_cliente, nome, cpf, telefone, endereco, email,login, senha, sexo, dataNascimento FROM Cliente";    
        return conexaoBanco.query(sql, (res, rowNum) -> 
        new Cliente(
            res.getInt("id_cliente"),
            res.getString("nome"),
            res.getString("cpf"),
            res.getString("telefone"),
            res.getString("endereco"),
            res.getString("email"),
            res.getString("login"),
            res.getString("senha"),
            res.getString("sexo"),
            res.getDate("dataNascimento").toLocalDate()
        ));
        
    }

    // Buscar por um cliente único
    public Cliente buscarPorId(Integer id) {
        String sql = "SELECT id_cliente, nome, cpf, telefone, endereco, email,login, senha, sexo, dataNascimento FROM Cliente WHERE id_cliente = ?";
        return conexaoBanco.queryForObject(sql, new BeanPropertyRowMapper<>(Cliente.class), id); 
    }

    // Buscar por nome
    public List<Cliente> buscarPorNome(String nome) {
        String sql = "SELECT id_cliente, nome, cpf, telefone, endereco, email,login, senha, sexo, dataNascimento FROM Cliente WHERE lower(nome) LIKE ?";
        return conexaoBanco.query(sql, new BeanPropertyRowMapper<>(Cliente.class), "%" + nome.toLowerCase() + "%");
    }

    //Buscar por login
    public Cliente buscarPorLogin(String login) {
        String sql = "SELECT id_cliente, nome, cpf, telefone, endereco, email,login, senha, sexo, dataNascimento FROM Cliente WHERE login = ?";
        return conexaoBanco.queryForObject(sql, new BeanPropertyRowMapper<>(Cliente.class), login);
    }

    //Adicionar um novo cliente
    public void adicionar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, cpf, telefone, endereco, email, login, senha, sexo, dataNascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        conexaoBanco.update(sql, 
            cliente.getNome(),  
            cliente.getCpf(), 
            cliente.getTelefone(), 
            cliente.getEndereco(),
            cliente.getEmail(),
            cliente.getLogin(),
            cliente.getSenha(),
            cliente.getSexo(),
            cliente.getDataNascimento()
        );
    }

    //deletar um cliente
    public void deletar(Integer id) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        conexaoBanco.update(sql, id);
    }

    // atualizar um cliente
    public boolean update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, cpf = ?, telefone = ?, endereco = ?, email = ?, login = ?, senha = ?, sexo = ?, dataNascimento = ? WHERE id_cliente = ?"; 
        return conexaoBanco.update(sql,
        cliente.getNome(),
        cliente.getCpf(),
        cliente.getTelefone(),
        cliente.getEndereco(),
        cliente.getEmail(),
        cliente.getLogin(),
        cliente.getSenha(),
        cliente.getSexo(),
        cliente.getDataNascimento()) > 0;
    }

}
