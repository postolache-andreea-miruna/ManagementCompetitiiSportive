package com.example.managementcompetitii.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
//import javax.persistence.*;
//import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

public class ParticipaId implements Serializable {
    private long idSportiv;
    private long idProba;
    private long idCompetitie;

    public ParticipaId() {
    }

    public ParticipaId(long idSportiv, long idProba, long idCompetitie) {
        this.idSportiv = idSportiv;
        this.idProba = idProba;
        this.idCompetitie = idCompetitie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipaId that = (ParticipaId) o;
        return Objects.equals(idSportiv, that.idSportiv) && Objects.equals(idProba, that.idProba) && Objects.equals(idCompetitie, that.idCompetitie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSportiv, idProba, idCompetitie);
    }

    @Override
    public String toString() {
        return "ParticipaId{" +
                "idSportiv=" + idSportiv +
                ", idProba=" + idProba +
                ", idCompetitie=" + idCompetitie +
                '}';
    }
}
