package iftm.pmvc.crud_proj.domain;

import java.time.LocalDate;

public class Cartao {

    private Integer id; 
    private Conta conta;
    private String numero;
    private LocalDate dataVencimento;
    private float limite;
    private String tipo;
    private int cvv; 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode()); 
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
        Cartao other = (Cartao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) 
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    public Cartao() {
    }

    public Cartao(Integer id, Conta conta, String numero, LocalDate dataVencimento, float limite, String tipo, int cvv) {
        this.id = id;
        this.conta = conta;
        this.numero = numero;
        this.dataVencimento = dataVencimento;
        this.limite = limite;
        this.tipo = tipo;
        this.cvv = cvv; 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataVencimento() { 
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) { 
        this.dataVencimento = dataVencimento;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCvv() { 
        return cvv;
    }

    public void setCvv(int cvv) { 
        this.cvv = cvv;
    }
}