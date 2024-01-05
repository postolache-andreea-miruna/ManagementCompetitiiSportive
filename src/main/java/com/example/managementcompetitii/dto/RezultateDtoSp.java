package com.example.managementcompetitii.dto;

public class RezultateDtoSp {
    private String numeCompetitie;
    private String numeProba;
    private Integer timp;
    private Integer locClasament;

    public RezultateDtoSp() {
    }

    public RezultateDtoSp(String numeCompetitie, String numeProba, Integer timp, Integer locClasament) {
        this.numeCompetitie = numeCompetitie;
        this.numeProba = numeProba;
        this.timp = timp;
        this.locClasament = locClasament;
    }

    public String getNumeCompetitie() {
        return numeCompetitie;
    }

    public void setNumeCompetitie(String numeCompetitie) {
        this.numeCompetitie = numeCompetitie;
    }

    public String getNumeProba() {
        return numeProba;
    }

    public void setNumeProba(String numeProba) {
        this.numeProba = numeProba;
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
