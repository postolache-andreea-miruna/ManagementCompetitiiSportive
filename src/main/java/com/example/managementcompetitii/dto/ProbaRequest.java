package com.example.managementcompetitii.dto;

import jakarta.validation.constraints.NotBlank;
//import javax.validation.constraints.*;
public class ProbaRequest {
    @NotBlank(message = "Numele nu poate fi null")
    private String nume;

    public ProbaRequest() {
    }

    public ProbaRequest(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
