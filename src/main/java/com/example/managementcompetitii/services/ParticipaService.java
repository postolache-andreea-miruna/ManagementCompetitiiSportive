package com.example.managementcompetitii.services;

import com.example.managementcompetitii.dto.*;
import com.example.managementcompetitii.model.Participa;
import com.example.managementcompetitii.model.ParticipaId;

import java.util.List;

public interface ParticipaService {
    List<RezultateDtoComp> getRezultateByCompetitieId(Long competitieId);
    List<RezultateDtoBestOf> getBestOfBySportivId(Long sportivId);
    List<RezultateDtoSpComp> getRezultateBySportivIdCompetitieId(Long sportivId, Long competitieId);
    List<RezultateDtoSp> getRezultateBySportivId(Long sportivId);
    Participa saveParticipa(Participa participa);
    Participa updateParticipa(ParticipaId id, ParticipaDtoUpdate participaDtoUpdate) throws Exception;
    void deleteParticipaById(ParticipaId id);
}
