package com.example.managementcompetitii.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Proba {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   // @NotNull
    @Column(name="nume", unique = true, nullable = false)
    private String nume;

    //Proba - Participa (1-M)
    @OneToMany(mappedBy = "proba")
    private List<Participa> participari;

    public Proba() {
    }

    public Proba(Integer id, String nume) {
        this.id = id;
        this.nume = nume;
    }


    public Proba(String nume) {
        this.nume = nume;
    }

    public Proba(String nume, List<Participa> participari) {
        this.nume = nume;
        this.participari = participari;
    }

    public long getId() {
        return id;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proba proba = (Proba) o;
        return Objects.equals(id, proba.id) && Objects.equals(nume, proba.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume);
    }

    @Override
    public String toString() {
        return "Proba{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}
