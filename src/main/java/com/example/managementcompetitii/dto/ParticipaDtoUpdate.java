package com.example.managementcompetitii.dto;

import jakarta.validation.constraints.NotNull;
//import javax.validation.constraints.*;
public class ParticipaDtoUpdate {
    @NotNull(message = "Timpul obtinut (s) nu poate fi null")
    private Integer timp;
    @NotNull(message = "Locul in clasament nu poate fi null")
    private Integer locClasament;

    public ParticipaDtoUpdate() {
    }

    public ParticipaDtoUpdate(Integer timp, Integer locClasament) {
        this.timp = timp;
        this.locClasament = locClasament;
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
