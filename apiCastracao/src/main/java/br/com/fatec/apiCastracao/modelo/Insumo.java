package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "Insumos")
public class Insumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insumoId;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100)
    private String tipo;

    @Column(length = 7)
    private double precoCusto;

    @Column
    private int qtdeEstoque;

    //relacionamento de Insumo e InsumosManejo
    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InsumosManejo> insumosManejos;

    //relacionamento de Insumo e Movimentacao
    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Movimentacao> movimentacoes;

    //métodos acessores
    public Integer getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(Integer insumoId) {
        this.insumoId = insumoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public List<InsumosManejo> getInsumosManejos() {
        return insumosManejos;
    }

    public void setInsumosManejos(List<InsumosManejo> insumosManejos) {
        this.insumosManejos = insumosManejos;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    //métodos equals e hashcode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Insumo insumo = (Insumo) o;
        return Objects.equals(insumoId, insumo.insumoId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(insumoId);
    }
}
