package com.example.managementcompetitii.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Sportiv {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   // @NotNull
    private String nume;
   // @NotNull
    private String prenume;
//    @NotNull(message = "Genul trebuie să fie specificat")
//    @Pattern(regexp = "M|F", message = "Genul trebuie să fie 'M' sau 'F'")
    private String gen;

   // @NotNull
    private Integer anNastere;
    private Double indemnizatie;


    @Column(name="nr_legitimatie", unique = true, nullable = false)
    //@NotNull
   // @Min(value = 11)
    private Integer nrLegitimatie; //>10

    //Antrenor - Sportiv (1-M)
    @ManyToOne
    @JoinColumn(name = "antrenor_id")
    private Antrenor antrenor;

    //Sportiv - Participa (1-M)
    @OneToMany(mappedBy = "sportiv")
    private List<Participa> participari;


    public Sportiv() {
    }

    public Sportiv(long id, String nume, String prenume, String gen, Integer anNastere, Double indemnizatie, Integer nrLegitimatie, Antrenor antrenor) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.anNastere = anNastere;
        this.indemnizatie = indemnizatie;
        this.nrLegitimatie = nrLegitimatie;
        this.antrenor = antrenor;
    }

    public Sportiv(String nume, String prenume, String gen, Integer anNastere, Double indemnizatie, Integer nrLegitimatie, Antrenor antrenor) {
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.anNastere = anNastere;
        this.indemnizatie = indemnizatie;
        this.nrLegitimatie = nrLegitimatie;
        this.antrenor = antrenor;
    }
    //    public Sportiv(long id, String nume, String prenume, String gen, Integer anNastere, Double indemnizatie, Integer nrLegitimatie, Antrenor antrenor, List<Participa> participari) {
//        this.id = id;
//        this.nume = nume;
//        this.prenume = prenume;
//        this.gen = gen;
//        this.anNastere = anNastere;
//        this.indemnizatie = indemnizatie;
//        this.nrLegitimatie = nrLegitimatie;
//        this.antrenor = antrenor;
//        this.participari = participari;
//    }


    public Sportiv(String nume, String prenume, String gen, Integer anNastere, Double indemnizatie, Integer nrLegitimatie, Antrenor antrenor, List<Participa> participari) {
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.anNastere = anNastere;
        this.indemnizatie = indemnizatie;
        this.nrLegitimatie = nrLegitimatie;
        this.antrenor = antrenor;
        this.participari = participari;
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

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Integer getAnNastere() {
        return anNastere;
    }

    public void setAnNastere(Integer anNastere) {
        this.anNastere = anNastere;
    }

    public Double getIndemnizatie() {
        return indemnizatie;
    }

    public void setIndemnizatie(Double indemnizatie) {
        this.indemnizatie = indemnizatie;
    }

    public Integer getNrLegitimatie() {
        return nrLegitimatie;
    }

    public void setNrLegitimatie(Integer nrLegitimatie) {
        this.nrLegitimatie = nrLegitimatie;
    }

    public Antrenor getAntrenor() {
        return antrenor;
    }

    public void setAntrenor(Antrenor antrenor) {
        this.antrenor = antrenor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sportiv sportiv = (Sportiv) o;
        return Objects.equals(id, sportiv.id) && Objects.equals(nume, sportiv.nume) && Objects.equals(prenume, sportiv.prenume) && Objects.equals(gen, sportiv.gen) && Objects.equals(anNastere, sportiv.anNastere) && Objects.equals(indemnizatie, sportiv.indemnizatie) && Objects.equals(nrLegitimatie, sportiv.nrLegitimatie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, prenume, gen, anNastere, indemnizatie, nrLegitimatie);
    }

    @Override
    public String toString() {
        return "Sportiv{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", gen=" + gen +
                ", anNastere=" + anNastere +
                ", indemnizatie=" + indemnizatie +
                ", nrLegitimatie=" + nrLegitimatie +
                '}';
    }
}
