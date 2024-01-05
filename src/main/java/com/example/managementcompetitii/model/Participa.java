package com.example.managementcompetitii.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
//import javax.persistence.*;
//import javax.validation.constraints.*;
@Entity
@IdClass(ParticipaId.class)//https://www.baeldung.com/jpa-composite-primary-keys, https://www.youtube.com/watch?v=8pG80eERkE8&ab_channel=JavaTechie
public class Participa {
    @Id
    @NotNull
    @Column(name = "id_sportiv")
    private long idSportiv;

    @Id
    @NotNull
    @Column(name = "id_proba")
    private long idProba;

    @Id
    @NotNull
    @Column(name = "id_competitie")
    private long idCompetitie;
    //@NotNull
    private Integer timp;
   // @NotNull
    private Integer locClasament;

    @ManyToOne
    @MapsId("idSportiv")
    @JoinColumn(name = "id_sportiv")
    private Sportiv sportiv;

    @ManyToOne
    @MapsId("idProba")
    @JoinColumn(name = "id_proba")
    private Proba proba;

    @ManyToOne
    @MapsId("idCompetitie")
    @JoinColumn(name = "id_competitie")
    private Competitie competitie;

    public Participa() {
    }

    public Participa(long idSportiv, long idProba, long idCompetitie, Integer timp, Integer locClasament, Sportiv sportiv, Proba proba, Competitie competitie) {
        this.idSportiv = idSportiv;
        this.idProba = idProba;
        this.idCompetitie = idCompetitie;
        this.timp = timp;
        this.locClasament = locClasament;
        this.sportiv = sportiv;
        this.proba = proba;
        this.competitie = competitie;
    }

    public Participa(long idSportiv, long idProba, long idCompetitie, Integer timp, Integer locClasament) {
        this.idSportiv = idSportiv;
        this.idProba = idProba;
        this.idCompetitie = idCompetitie;
        this.timp = timp;
        this.locClasament = locClasament;
    }

    public long getIdSportiv() {
        return idSportiv;
    }

    public void setIdSportiv(long idSportiv) {
        this.idSportiv = idSportiv;
    }

    public long getIdProba() {
        return idProba;
    }

    public void setIdProba(long idProba) {
        this.idProba = idProba;
    }

    public long getIdCompetitie() {
        return idCompetitie;
    }

    public void setIdCompetitie(long idCompetitie) {
        this.idCompetitie = idCompetitie;
    }

    public Integer getTimp() {
        return timp;
    }

    public void setTimp(Integer timp) {
        this.timp = timp;
    }

    public Integer getLocClasament() {
        return locClasament;
    }

    public void setLocClasament(Integer locClasament) {
        this.locClasament = locClasament;
    }

    public Sportiv getSportiv() {
        return sportiv;
    }

    public void setSportiv(Sportiv sportiv) {
        this.sportiv = sportiv;
    }

    public Proba getProba() {
        return proba;
    }

    public void setProba(Proba proba) {
        this.proba = proba;
    }

    public Competitie getCompetitie() {
        return competitie;
    }

    public void setCompetitie(Competitie competitie) {
        this.competitie = competitie;
    }

    @Override
    public String toString() {
        return "Participa{" +
                "idSportiv=" + idSportiv +
                ", idProba=" + idProba +
                ", idCompetitie=" + idCompetitie +
                ", timp='" + timp + '\'' +
                ", locClasament=" + locClasament +
                ", sportiv=" + sportiv +
                ", proba=" + proba +
                ", competitie=" + competitie +
                '}';
    }
}
