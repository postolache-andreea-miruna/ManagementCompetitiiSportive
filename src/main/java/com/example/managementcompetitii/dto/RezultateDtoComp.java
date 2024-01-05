package com.example.managementcompetitii.dto;

import com.example.managementcompetitii.model.Participa;

public class RezultateDtoComp {
    private Integer nrLegitimatie;
    private String numeSportiv;
    private String prenumeSportiv;
    private Integer timp;
    private Integer locClasament;
    private String numeProba;

    public RezultateDtoComp() {
    }

    public RezultateDtoComp(Integer nrLegitimatie, String numeSportiv, String prenumeSportiv, Integer timp, Integer locClasament, String numeProba) {
        this.nrLegitimatie = nrLegitimatie;
        this.numeSportiv = numeSportiv;
        this.prenumeSportiv = prenumeSportiv;
        this.timp = timp;
        this.locClasament = locClasament;
        this.numeProba = numeProba;
    }

    public Integer getNrLegitimatie() {
        return nrLegitimatie;
    }

    public void setNrLegitimatie(Integer nrLegitimatie) {
        this.nrLegitimatie = nrLegitimatie;
    }

    public String getNumeSportiv() {
        return numeSportiv;
    }

    public void setNumeSportiv(String numeSportiv) {
        this.numeSportiv = numeSportiv;
    }

    public String getPrenumeSportiv() {
        return prenumeSportiv;
    }

    public void setPrenumeSportiv(String prenumeSportiv) {
        this.prenumeSportiv = prenumeSportiv;
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

    public String getNumeProba() {
        return numeProba;
    }

    public void setNumeProba(String numeProba) {
        this.numeProba = numeProba;
    }


}
