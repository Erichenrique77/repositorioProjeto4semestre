package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Agendamentos")
public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agendamentoId;

    @Column(length = 1000)
    private String observacoes;

    @Column
    private Date dataPrevista;


    //relacionamento Agendamento e Animal
    @ManyToOne
    @JoinColumn(name = "animal")
    @JsonBackReference
    private Animal animal;

    //relacionamento Agendamento e Responsavel
    @ManyToOne
    @JoinColumn(name = "responsavel")
    @JsonBackReference
    private Responsavel responsavel;

    //relacionamento Agendamento e ItensManejo
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItensManejo> itensManejos;

    //métodos acessores

    public Integer getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(Integer agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public List<ItensManejo> getItensManejos() {
        return itensManejos;
    }

    public void setItensManejos(List<ItensManejo> itensManejos) {
        this.itensManejos = itensManejos;
    }

    //equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(agendamentoId, that.agendamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(agendamentoId);
    }
}
