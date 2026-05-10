package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "InsumosManejo")
public class InsumosManejo  implements Serializable {
    private static final long serialVersion =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int qtde;

    //relacionamento InsumosManejo com Insumos
    @ManyToOne
    @JoinColumn(name = "insumo")
    @JsonBackReference
    private Insumo insumo;

    //relacionamento InsumosManejo com Manejo
    @ManyToOne
    @JoinColumn(name = "manejo")
    @JsonBackReference
    private Manejo manejo;

    public InsumosManejo(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Manejo getManejo() {
        return manejo;
    }

    public void setManejo(Manejo manejo) {
        this.manejo = manejo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsumosManejo that = (InsumosManejo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
