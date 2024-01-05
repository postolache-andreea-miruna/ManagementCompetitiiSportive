package com.example.managementcompetitii.dto;

public class RezultateDtoSpComp {
    private String numeProba;
    private Integer timp;
    private Integer locClasament;

    public RezultateDtoSpComp() {
    }

    public RezultateDtoSpComp(String numeProba, Integer timp, Integer locClasament) {
        this.numeProba = numeProba;
        this.timp = timp;
        this.locClasament = locClasament;
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
