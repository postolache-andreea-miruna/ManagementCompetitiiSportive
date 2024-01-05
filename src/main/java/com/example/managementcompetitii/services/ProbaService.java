package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.ParticipaDtoUpdate;
import com.example.managementcompetitii.model.Participa;
import com.example.managementcompetitii.model.ParticipaId;
import com.example.managementcompetitii.model.Proba;

import java.util.List;

public interface ProbaService {
    Proba saveProba(Proba proba);
    List<Proba> getAllProbe();
}
