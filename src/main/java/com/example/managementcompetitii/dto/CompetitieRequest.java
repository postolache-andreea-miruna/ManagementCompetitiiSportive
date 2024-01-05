package com.example.managementcompetitii.dto;

import com.example.managementcompetitii.model.Tip;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.*;
import java.util.Date;

public class CompetitieRequest {
    @NotBlank(message = "Numele nu poate fi null")
    private String nume;
    @NotNull(message = "Data de start nu poate lipsi")
    private Date dataStart;
    @NotNull(message = "Data de final nu poate lipsi")
    private Date dataFinal;
    @NotNull(message = "Taxa de participare nu poate fi null")
    private Double taxaParticipare;
    private Tip tip;

    public CompetitieRequest() {
    }

    public CompetitieRequest(String nume, Date dataStart, Date dataFinal, Double taxaParticipare, Tip tip) {
        this.nume = nume;
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.taxaParticipare = taxaParticipare;
        this.tip = tip;
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

    public Double getTaxaParticipare() {
        return taxaParticipare;
    }

    public void setTaxaParticipare(Double taxaParticipare) {
        this.taxaParticipare = taxaParticipare;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
