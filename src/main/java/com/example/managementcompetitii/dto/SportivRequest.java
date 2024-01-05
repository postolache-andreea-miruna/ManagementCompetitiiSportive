package com.example.managementcompetitii.dto;

import com.example.managementcompetitii.model.Antrenor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
//import javax.validation.constraints.*;
public class SportivRequest {
    @NotBlank(message = "Numele nu poate fi null")
    private String nume;
    @NotBlank(message = "Prenumele nu poate fi null")
    private String prenume;
    @NotNull(message = "Genul trebuie să fie specificat")
    @Pattern(regexp = "M|F", message = "Genul trebuie să fie 'M' sau 'F'")
    private String gen;
    @NotNull(message = "Anul nasterii nu poate fi null")
    private Integer anNastere;
    private Double indemnizatie;
    @NotNull(message = "Nr legitimatiei nu poate fi null")
    @Min(value = 11,message = "Legitimatiile incep de la numarul 11")
    private Integer nrLegitimatie;
    private Antrenor antrenor;

    public SportivRequest() {
    }

    public SportivRequest(String nume, String prenume, String gen, Integer anNastere, Double indemnizatie, Integer nrLegitimatie, Antrenor antrenor) {
        this.nume = nume;
        this.prenume = prenume;
        this.gen = gen;
        this.anNastere = anNastere;
        this.indemnizatie = indemnizatie;
        this.nrLegitimatie = nrLegitimatie;
        this.antrenor = antrenor;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Integer getAnNastere() {
        return anNastere;
    }

    public void setAnNastere(Integer anNastere) {
        this.anNastere = anNastere;
    }

    public Double getIndemnizatie() {
        return indemnizatie;
    }

    public void setIndemnizatie(Double indemnizatie) {
        this.indemnizatie = indemnizatie;
    }

    public Integer getNrLegitimatie() {
        return nrLegitimatie;
    }

    public void setNrLegitimatie(Integer nrLegitimatie) {
        this.nrLegitimatie = nrLegitimatie;
    }

    public Antrenor getAntrenor() {
        return antrenor;
    }

    public void setAntrenor(Antrenor antrenor) {
        this.antrenor = antrenor;
    }
}
