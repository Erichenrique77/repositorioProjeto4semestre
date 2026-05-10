package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Responsavel")
public class Responsavel implements Serializable {
    private static final long serialVersion =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responsavelId;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String telefone;

    @Column(length = 25, nullable = false)
    private String tipo;

    public Responsavel(){

    }

    //relacionamento Responsavel e Agendamento
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Agendamento> agendamentos;

    //métodos acessores

    public Integer getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Integer responsavelId) {
        this.responsavelId = responsavelId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    //métodos equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Responsavel that = (Responsavel) o;
        return Objects.equals(responsavelId, that.responsavelId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(responsavelId);
    }
}
