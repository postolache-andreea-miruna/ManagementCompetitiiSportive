package com.example.managementcompetitii.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Entity;

//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Antrenor {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@NotNull
    private String nume;
    //@NotNull
    private String prenume;
    @Column(name = "ani_experienta")
//    @NotNull
//    @Min(value = 0, message = "Anii de experiență trebuie să fie un numar pozitiv")
    private Integer aniExperienta; //trebuie sa fie mai mari ca 0

//    @Min(value = 0, message = "Salariul trebuie să fie un numar pozitiv")
//    @NotNull
    private Float salariu;//mai mare ca 0
//    @NotNull(message = "Genul trebuie să fie specificat")
   // @Pattern(regexp = "M|F", message = "Genul trebuie să fie 'M' sau 'F'")
    private String gen;

    //Club - Antrenor (1-M)
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    //Antrenor - Sportiv (1-M)
    @OneToMany(mappedBy = "antrenor")
    private List<Sportiv> sportivi;

    public Antrenor() {
    }

//    public Antrenor(long id, String nume, String prenume, Integer aniExperienta, Float salariu, String gen, Club club) {
//        this.id = id;
//        this.nume = nume;
//        this.prenume = prenume;
//        this.aniExperienta = aniExperienta;
//        this.salariu = salariu;
//        this.gen = gen;
//        this.club = club;
//    }


    public Antrenor(long id, String nume, String prenume, Integer aniExperienta, Float salariu, String gen, Club club) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.aniExperienta = aniExperienta;
        this.salariu = salariu;
        this.gen = gen;
        this.club = club;
    }

    public Antrenor(String nume, String prenume, Integer aniExperienta, Float salariu, String gen, Club club) {
        this.nume = nume;
        this.prenume = prenume;
        this.aniExperienta = aniExperienta;
        this.salariu = salariu;
        this.gen = gen;
        this.club = club;
    }

    public Antrenor(long id) {
        this.id = id;
    }

    public Antrenor(String nume, String prenume, Integer aniExperienta, Float salariu, String gen, Club club, List<Sportiv> sportivi) {
        this.nume = nume;
        this.prenume = prenume;
        this.aniExperienta = aniExperienta;
        this.salariu = salariu;
        this.gen = gen;
        this.club = club;
        this.sportivi = sportivi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Integer getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(Integer aniExperienta) {
        this.aniExperienta = aniExperienta;
    }

    public Float getSalariu() {
        return salariu;
    }

    public void setSalariu(Float salariu) {
        this.salariu = salariu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Antrenor antrenor = (Antrenor) o;
        return Objects.equals(id, antrenor.id) && Objects.equals(nume, antrenor.nume) && Objects.equals(prenume, antrenor.prenume) && Objects.equals(aniExperienta, antrenor.aniExperienta) && Objects.equals(salariu, antrenor.salariu) && Objects.equals(gen, antrenor.gen) && Objects.equals(club, antrenor.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, aniExperienta, salariu, gen, club);
    }

    @Override
    public String toString() {
        return "Antrenor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", aniExperienta=" + aniExperienta +
                ", salariu=" + salariu +
                ", gen=" + gen +
                ", club=" + club +
                '}';
    }
}
