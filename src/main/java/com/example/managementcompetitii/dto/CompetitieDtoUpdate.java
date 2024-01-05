package com.example.managementcompetitii.dto;

import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.*;
import java.util.Date;

public class CompetitieDtoUpdate {
//    private long id;
    @NotNull(message = "Data de start nu poate lipsi")
    private Date dataStart;
    @NotNull(message = "Data de final nu poate lipsi")
    private Date dataFinal;
    @NotNull(message = "Taxa de participare nu poate fi null")
    private Double taxaParticipare;

    public CompetitieDtoUpdate() {
    }

    public CompetitieDtoUpdate(Date dataStart, Date dataFinal, Double taxaParticipare) {
        this.dataStart = dataStart;
        this.dataFinal = dataFinal;
        this.taxaParticipare = taxaParticipare;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

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
}
