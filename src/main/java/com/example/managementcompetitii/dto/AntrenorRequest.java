package com.example.managementcompetitii.dto;

import com.example.managementcompetitii.model.Club;

//import javax.validation.constraints.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AntrenorRequest {
    @NotBlank(message = "Numele nu poate fi null")
    private String nume;
    @NotBlank(message = "Prenumele nu poate fi null")
    private String prenume;
    @NotNull(message = "Nr de ani de experienta nu poate fi null")
    @Min(value = 0, message = "Anii de experiență trebuie să fie un numar pozitiv")
    private Integer aniExperienta;
    @Min(value = 0, message = "Salariul trebuie să fie un numar pozitiv")
    @NotNull(message = "Salariul nu poate fi null")
    private Float salariu;
    @NotNull(message = "Genul trebuie să fie specificat")
    @Pattern(regexp = "M|F", message = "Genul trebuie să fie 'M' sau 'F'")
    private String gen;

    private Club club;

    public AntrenorRequest(String nume, String prenume, Integer aniExperienta, Float salariu, String gen, Club club) {
        this.nume = nume;
        this.prenume = prenume;
        this.aniExperienta = aniExperienta;
        this.salariu = salariu;
        this.gen = gen;
        this.club = club;
    }

    public AntrenorRequest() {
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

    public Integer getAniExperienta() {
        return aniExperienta;
    }

    public void setAniExperienta(Integer aniExperienta) {
        this.aniExperienta = aniExperienta;
    }

    public Float getSalariu() {
        return salariu;
    }

    public void setSalariu(Float salariu) {
        this.salariu = salariu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
