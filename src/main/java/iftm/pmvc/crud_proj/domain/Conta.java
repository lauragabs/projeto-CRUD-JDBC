package iftm.pmvc.crud_proj.domain;
import java.time.LocalDate;

public class Conta {
    private Integer id; 
    private String tipoConta;
    private float saldo;
    private Cliente cliente;
    private LocalDate dataCriacao;

    public Conta() {
    }

    public Conta(Integer id) {
        this.id = id;
    }

    public Conta(Integer id, Cliente cliente, String tipoConta, float saldo, LocalDate dataCriacao) { 
        this.id = id;
        this.tipoConta = tipoConta;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id != null ? id.hashCode() : 0); 
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) 
            return false;
        return true;
    }

    public Integer getId() { 
        return id;
    }

    public void setId(Integer id) { 
        this.id = id;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}