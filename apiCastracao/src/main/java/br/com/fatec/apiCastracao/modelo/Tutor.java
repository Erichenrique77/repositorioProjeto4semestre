package br.com.fatec.apiCastracao.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tutor")
public class Tutor implements Serializable {
    private static final long serialVersion =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tutorId;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 250, nullable = false)
    private String endereco;

    @Column(length = 16, nullable = false)
    private String celular;

    //relacionamento com Animal
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Animal> animais;

    public Tutor(){

    }

    //métodos acessores
    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    //métodos equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(tutorId, tutor.tutorId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tutorId);
    }
}
