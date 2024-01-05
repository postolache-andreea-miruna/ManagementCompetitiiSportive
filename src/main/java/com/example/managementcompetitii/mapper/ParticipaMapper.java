package com.example.managementcompetitii.mapper;

import com.example.managementcompetitii.dto.ParticipaRequest;
import com.example.managementcompetitii.dto.ProbaRequest;
import com.example.managementcompetitii.model.Participa;
import com.example.managementcompetitii.model.Proba;
import org.springframework.stereotype.Component;

@Component
public class ParticipaMapper {
    public Participa participaRequestToParticipa(ParticipaRequest participaRequest){
        return new Participa(participaRequest.getIdSportiv(), participaRequest.getIdProba(), participaRequest.getIdCompetitie(),
                participaRequest.getTimp(), participaRequest.getLocClasament());
    }
}
