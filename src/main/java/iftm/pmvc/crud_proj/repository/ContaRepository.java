package iftm.pmvc.crud_proj.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iftm.pmvc.crud_proj.domain.Cliente;
import iftm.pmvc.crud_proj.domain.Conta;

@Repository
public class ContaRepository {

    private JdbcTemplate conexaoBanco;

    public ContaRepository(JdbcTemplate conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    public List<Conta> listar() {
        String sql = "SELECT id_conta, id_cliente, tipo, saldo, dataCriacao FROM Conta";
        return conexaoBanco.query(sql, (res, rowNum) -> new Conta(
            res.getInt("id_conta"),
            new Cliente(res.getInt("id_cliente")),
            res.getString("tipo"),
            res.getFloat("saldo"),
            res.getDate("dataCriacao").toLocalDate()
        ));
    }

    public Conta buscarPorId(Integer id) {
        String sql = "SELECT id_conta, id_cliente, tipo, saldo, dataCriacao FROM Conta WHERE id_conta = ?";
        return conexaoBanco.queryForObject(sql, new BeanPropertyRowMapper<>(Conta.class), id);
    }

    public void adicionar(Conta conta) {
        String sql = "INSERT INTO Conta (id_cliente, tipo, saldo, dataCriacao) VALUES (?, ?, ?, ?)";
        conexaoBanco.update(sql, 
            conta.getCliente().getId(), 
            conta.getTipoConta(),
            conta.getSaldo(),
            conta.getDataCriacao()
        );
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM Conta WHERE id_conta = ?";
        conexaoBanco.update(sql, id);
    }

    public boolean update(Conta conta) {
        String sql = "UPDATE Conta SET id_cliente = ?, tipo = ?, saldo = ?, dataCriacao = ? WHERE id_conta = ?";
        return conexaoBanco.update(sql, 
            conta.getCliente().getId(), 
            conta.getTipoConta(), 
            conta.getSaldo(),
            conta.getDataCriacao(),
            conta.getId()) > 0;
    }
}