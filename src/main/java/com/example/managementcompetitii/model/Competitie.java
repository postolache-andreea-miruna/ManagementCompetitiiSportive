package com.example.managementcompetitii.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Competitie {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@NotNull
    @Column(name="nume", unique = true, nullable = false)
    private String nume;
    //@NotNull
    private Date dataStart;
   // @NotNull
    private Date dataFinal;
    //@NotNull
    @Column(name="taxa_participare", nullable = false)
    private Double taxaParticipare;

    //TIP-COMPEITIE (1-M)
    @ManyToOne
    @JoinColumn(name = "tip_id")
    private Tip tip;

    //Competitie - Participa (1-M)
    @OneToMany(mappedBy = "competitie")
    private List<Participa> participari;

    public Competitie() {
    }

    public void setId(long id) {
        this.id = id;
    }
//    public Competitie(long id, String nume, Date dataStart, Date dataFinal, Double taxaParticipare, Tip tip, List<Participa> participari) {
//        this.id = id;
//        this.nume = nume;
//        this.dataStart = dataStart;
//        this.dataFinal = dataFinal;
//        this.taxaParticipare = taxaParticipare;
//        this.tip = tip;
//        this.participari = participari;
//    }


    public Competitie(String nume, Date dataStart, Date dataFinal, Double taxaParticipare, Tip tip) {
        this.nume = nume;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.taxaParticipare = taxaParticipare;
        this.tip = tip;
    }

    public Competitie(long id, String nume, Date dataStart, Date dataFinal, Double taxaParticipare, Tip tip) {
        this.id = id;
        this.nume = nume;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.taxaParticipare = taxaParticipare;
        this.tip = tip;
    }

    public Competitie(String nume, Date dataStart, Date dataFinal, Double taxaParticipare, Tip tip, List<Participa> participari) {
        this.nume = nume;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.taxaParticipare = taxaParticipare;
        this.tip = tip;
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

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Double getTaxaParticipare() {
        return taxaParticipare;
    }

    public void setTaxaParticipare(Double taxaParticipare) {
        this.taxaParticipare = taxaParticipare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitie that = (Competitie) o;
        return Objects.equals(id, that.id) && Objects.equals(nume, that.nume) && Objects.equals(dataStart, that.dataStart) && Objects.equals(dataFinal, that.dataFinal) && Objects.equals(taxaParticipare, that.taxaParticipare) && Objects.equals(tip, that.tip) && Objects.equals(participari, that.participari);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, dataStart, dataFinal, taxaParticipare, tip, participari);
    }

    @Override
    public String toString() {
        return "Competitie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", dataStart=" + dataStart +
                ", dataFinal=" + dataFinal +
                ", taxaParticipare=" + taxaParticipare +
                ", tip=" + tip +
               // ", participari=" + participari +
                '}';
    }
}