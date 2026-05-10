package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ItensManejo")
public class ItensManejo  implements Serializable {
    private static final long serialVersion =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itensManejoId;

    //relacionamento ItensManejo com Agendamento
    @ManyToOne
    @JoinColumn(name = "agendamento")
    @JsonBackReference
    private Agendamento agendamento;

    //relacionamento ItensManejo com Manejo
    @ManyToOne
    @JoinColumn(name = "manejo")
    @JsonBackReference
    private Manejo manejo;

    public ItensManejo(){

    }

    public Integer getItensManejoId() {
        return itensManejoId;
    }

    public void setItensManejoId(Integer itensManejoId) {
        this.itensManejoId = itensManejoId;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
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
        ItensManejo that = (ItensManejo) o;
        return Objects.equals(itensManejoId, that.itensManejoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itensManejoId);
    }
}
