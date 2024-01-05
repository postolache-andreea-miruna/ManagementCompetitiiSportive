package com.example.managementcompetitii.dto;

import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.*;
public class ParticipaRequest {
    private long idSportiv;
    private long idProba;
    private long idCompetitie;
    @NotNull(message = "Timpul obtinut (s) nu poate fi null")
    private Integer timp;
    @NotNull(message = "Locul in clasament nu poate fi null")
    private Integer locClasament;

    public ParticipaRequest() {
    }

    public ParticipaRequest(long idSportiv, long idProba, long idCompetitie, Integer timp, Integer locClasament) {
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
}
