package com.example.managementcompetitii.services;

import com.example.managementcompetitii.model.Sportiv;

import java.util.List;

public interface SportivService {
    Sportiv saveSportiv(Sportiv sportiv);
    Sportiv updateSportiv(Long id, Sportiv sportiv);
    List<Sportiv> getSportiviByIdAntrenor(Long idAntrenor);
    List<Sportiv> getSportiviByIdClub(Long idClub);
}
