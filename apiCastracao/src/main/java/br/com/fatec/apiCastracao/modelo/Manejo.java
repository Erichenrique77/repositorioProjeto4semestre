package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Manejo")
public class Manejo implements Serializable {
    private static final long serialVersion =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer manejoId;

    @Column(length = 100, nullable = false)
    private String nome;


    //relacionamento de Manejo e ItensManejo
    @OneToMany(mappedBy = "manejo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItensManejo> itensManejos;

    //relacionamento entre Manejo e InsumosManejo
    @OneToMany(mappedBy = "manejo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InsumosManejo> insumosManejos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getManejoId() {
        return manejoId;
    }

    public void setManejoId(Integer manejoId) {
        this.manejoId = manejoId;
    }

    public List<ItensManejo> getItensManejos() {
        return itensManejos;
    }

    public void setItensManejos(List<ItensManejo> itensManejos) {
        this.itensManejos = itensManejos;
    }

    public List<InsumosManejo> getInsumosManejos() {
        return insumosManejos;
    }

    public void setInsumosManejos(List<InsumosManejo> insumosManejos) {
        this.insumosManejos = insumosManejos;
    }

    //equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Manejo manejo = (Manejo) o;
        return Objects.equals(manejoId, manejo.manejoId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(manejoId);
    }
}
