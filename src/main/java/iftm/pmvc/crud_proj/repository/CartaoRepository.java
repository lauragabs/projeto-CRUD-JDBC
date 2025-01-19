package iftm.pmvc.crud_proj.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iftm.pmvc.crud_proj.domain.Cartao; 
import iftm.pmvc.crud_proj.domain.Conta;
import jakarta.annotation.PostConstruct;

@Repository
public class CartaoRepository { 

    private JdbcTemplate conexaoBanco;

    public CartaoRepository( JdbcTemplate conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    public List<Cartao> listar() {
        String sql = "SELECT id_cartao, id_conta, numero, dataVencimento,limite, tipo, cvv FROM Cartao"; 
        return conexaoBanco.query(sql, (res, rowNum) -> new Cartao(
            res.getInt("id_cartao"),
            new Conta(res.getInt("id_conta")),
            res.getString("numero"),
            res.getDate("dataVencimento").toLocalDate(),
            res.getFloat("limite"),
            res.getString("tipo"),
            res.getInt("cvv")
        ));
    }

    public Cartao buscarPorId(Integer id) {
        String sql = "SELECT id_cartao, id_conta, numero, dataVencimento, limite, tipo, cvv FROM Cartao WHERE id_cartao = ?";
        return conexaoBanco.queryForObject(sql, new BeanPropertyRowMapper<>(Cartao.class), id);
    }

    public void adicionar(Cartao cartao) {
        String sql = "INSERT INTO Cartao (id_conta, numero, dataVencimento, limite, tipo, cvv) VALUES (?, ?, ?, ?, ?, ?)";
        conexaoBanco.update(sql, 
            cartao.getConta().getId(),
            cartao.getNumero(),
            cartao.getDataVencimento(),
            cartao.getLimite(),
            cartao.getTipo(),
            cartao.getCvv()
        );
    }

    public void deletar(Integer id) {
        String sql = "DELETE FROM Cartao WHERE id_cartao = ?";
        conexaoBanco.update(sql, id);
    }

    public boolean update(Cartao cartao) {
        String sql = "UPDATE Cartao SET id_conta = ?, numero = ?, dataVencimento = ?, limite = ?, tipo = ?, cvv = ? WHERE id_cartao = ?";
        return conexaoBanco.update(sql, 
            cartao.getConta().getId(),
            cartao.getNumero(),
            cartao.getDataVencimento(),
            cartao.getLimite(),
            cartao.getTipo(),
            cartao.getCvv(),
            cartao.getId()) > 0;
    }
}