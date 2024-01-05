package com.example.managementcompetitii.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.*;
public class ClubRequest {
    @NotBlank(message = "Numele nu poate fi null")
    private String nume;

    @Min(value = 100)
    @NotNull(message = "Nr de inregistrare nu poate fi null")
    private Integer nrInregistrare;

    public ClubRequest() {
    }

    public ClubRequest(String nume, Integer nrInregistrare) {
        this.nume = nume;
        this.nrInregistrare = nrInregistrare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getNrInregistrare() {
        return nrInregistrare;
    }

    public void setNrInregistrare(Integer nrInregistrare) {
        this.nrInregistrare = nrInregistrare;
    }
}
