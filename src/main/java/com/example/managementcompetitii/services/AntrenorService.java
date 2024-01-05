package com.example.managementcompetitii.services;

import com.example.managementcompetitii.model.Antrenor;

import java.util.List;

public interface AntrenorService {
    Antrenor saveAntrenor(Antrenor antrenor);
    Antrenor updateAntrenor(Long id, Antrenor antrenor);
    List<Antrenor> getAntrenoriAscNume();
    List<Antrenor> getAntrenoriDescNume();
    List<Antrenor> getAntrenoriFilterGenExperienta(String gen, Integer aniExperienta);
    List<Antrenor> getAntrenoriByIdClub(Long idClub);
}
