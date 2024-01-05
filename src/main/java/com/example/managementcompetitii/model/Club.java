package com.example.managementcompetitii.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;
@Entity
    public class Club {
        @Id
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name="nume", unique = true, nullable = false)
        //@NotNull
        private String nume;

        @Column(name = "nr_inregistrare", unique = true, nullable = false)
        //@NotNull
        private Integer nrInregistrare;

    //Club - Antrenor (1-M)
    @OneToMany(mappedBy = "club")
    private List<Antrenor> antrenori;


    public Club() {
    }

    public Club(long id, String nume, Integer nrInregistrare, List<Antrenor> antrenori) {
        this.id = id;
        this.nume = nume;
        this.nrInregistrare = nrInregistrare;
        this.antrenori = antrenori;
    }

    public Club(String nume, Integer nrInregistrare) {
        this.nume = nume;
        this.nrInregistrare = nrInregistrare;
    }
//    public Club(long id, String nume, Integer nrInregistrare) {
//        this.id = id;
//        this.nume = nume;
//        this.nrInregistrare = nrInregistrare;
//    }

    public Club(String nume, Integer nrInregistrare, List<Antrenor> antrenori) {
        this.nume = nume;
        this.nrInregistrare = nrInregistrare;
        this.antrenori = antrenori;
    }

    public Club(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Integer getNrInregistrare() {
        return nrInregistrare;
    }

    public void setNrInregistrare(Integer nrInregistrare) {
        this.nrInregistrare = nrInregistrare;
    }

    public Club(long id, String nume, Integer nrInregistrare) {
        this.id = id;
        this.nume = nume;
        this.nrInregistrare = nrInregistrare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return Objects.equals(id, club.id) && Objects.equals(nume, club.nume) && Objects.equals(nrInregistrare, club.nrInregistrare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, nrInregistrare);
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", nrInregistrare=" + nrInregistrare +
                '}';
    }
}
