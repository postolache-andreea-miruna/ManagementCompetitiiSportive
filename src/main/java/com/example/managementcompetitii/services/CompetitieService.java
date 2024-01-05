package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.CompetitieDtoUpdate;
import com.example.managementcompetitii.model.Competitie;

import java.util.List;

public interface CompetitieService {
    Competitie saveCompetitie(Competitie competitie);
    List<Competitie> getAllCompetitii();
    Competitie updateCompetitie(Long id, CompetitieDtoUpdate competitie);
    void deleteById(Long id);
    List<Competitie> getCompetitiiYearStartTip(Integer anStart, String numeTip);
}
