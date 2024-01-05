package com.example.managementcompetitii.dto;

import java.util.Date;

public class RezultateDtoBestOf {

    private String numeProba;
    private String numeCompetitie;
    private Date dataStart;
    private Date dataFinal;
    private Integer timp;

    public RezultateDtoBestOf() {
    }

    public RezultateDtoBestOf(String numeProba, String numeCompetitie, Date dataStart, Date dataFinal, Integer timp) {
        this.numeProba = numeProba;
        this.numeCompetitie = numeCompetitie;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.timp = timp;
    }

    public RezultateDtoBestOf(RezultateDtoBestOf rezultateDtoBestOf) {
    }

    public String getNumeProba() {
        return numeProba;
    }

    public void setNumeProba(String numeProba) {
        this.numeProba = numeProba;
    }

    public String getNumeCompetitie() {
        return numeCompetitie;
    }

    public void setNumeCompetitie(String numeCompetitie) {
        this.numeCompetitie = numeCompetitie;
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

    public Integer getTimp() {
        return timp;
    }

    public void setTimp(Integer timp) {
        this.timp = timp;
    }
}
