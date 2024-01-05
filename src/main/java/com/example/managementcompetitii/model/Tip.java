package com.example.managementcompetitii.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.util.*;

@Entity
public class Tip {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@NotNull
    @Column(name="nume", unique = true, nullable = false)
    private String nume;

    //TIP-COMPEITIE (1-M)
    @OneToMany(mappedBy = "tip")
    private List<Competitie> competitii;



    public Tip() {
    }

//    public Tip(long id, String nume) {
//        this.id = id;
//        this.nume = nume;
//    }


    public Tip(String nume) {
        this.nume = nume;
    }

    public Tip(long id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Tip(String nume, List<Competitie> competitii) {
        this.nume = nume;
        this.competitii = competitii;
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
        Tip tip = (Tip) o;
        return Objects.equals(id, tip.id) && Objects.equals(nume, tip.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume);
    }

    @Override
    public String toString() {
        return "Tip{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }
}
